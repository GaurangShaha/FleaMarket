package com.flea.market.data.common.remote.call

import com.flea.market.data.common.remote.mapper.toInternetConnectionExceptionOrSelf
import com.flea.market.foundation.helper.executeCatching
import com.flea.market.foundation.model.Result
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import okhttp3.Request
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import retrofit2.awaitResponse

@Suppress("TooGenericExceptionCaught")
internal class ResultWithResponseCall<T : Any>(
    private val proxy: Call<T?>,
    private val coroutineScope: CoroutineScope,
) : Call<Result<Response<T?>, Throwable>> {

    override fun enqueue(callback: Callback<Result<Response<T?>, Throwable>>) {
        coroutineScope.launch {
            try {
                val response = proxy.awaitResponse()
                callback.onResponse(
                    this@ResultWithResponseCall,
                    Response.success(response.toResult())
                )
            } catch (e: Exception) {
                coroutineContext.ensureActive()
                callback.onResponse(
                    this@ResultWithResponseCall,
                    Response.success(Result.failure(e.toInternetConnectionExceptionOrSelf()))
                )
            }
        }
    }

    override fun execute(): Response<Result<Response<T?>, Throwable>> =
        runBlocking(coroutineScope.coroutineContext) {
            try {
                Response.success(proxy.execute().toResult())
            } catch (e: Exception) {
                coroutineContext.ensureActive()
                Response.success(Result.failure(e.toInternetConnectionExceptionOrSelf()))
            }
        }

    override fun clone(): Call<Result<Response<T?>, Throwable>> =
        ResultWithResponseCall(proxy.clone(), coroutineScope)

    override fun request(): Request = proxy.request()

    override fun timeout(): Timeout = proxy.timeout()

    override fun isExecuted(): Boolean = proxy.isExecuted

    override fun isCanceled(): Boolean = proxy.isCanceled

    override fun cancel() = proxy.cancel()

    @Suppress("UNCHECKED_CAST")
    private fun <T> Response<T?>.toResult(): Result<Response<T?>, Throwable> {
        return executeCatching {
            if (isSuccessful) {
                this
            } else {
                throw HttpException(this)
            }
        }
    }
}
