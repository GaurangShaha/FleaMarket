package com.flea.market.data.common.remote.call

import com.flea.market.data.common.remote.mapper.toInternetConnectionExceptionOrSelf
import com.flea.market.foundation.helper.executeSafely
import com.flea.market.foundation.model.Result
import com.flea.market.foundation.model.Result.Failure
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
) : Call<Result<Response<T?>>> {

    override fun enqueue(callback: Callback<Result<Response<T?>>>) {
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
                    Response.success(Failure(e.toInternetConnectionExceptionOrSelf()))
                )
            }
        }
    }

    override fun execute(): Response<Result<Response<T?>>> =
        runBlocking(coroutineScope.coroutineContext) {
            try {
                Response.success(proxy.execute().toResult())
            } catch (e: Exception) {
                coroutineContext.ensureActive()
                Response.success(Failure(e.toInternetConnectionExceptionOrSelf()))
            }
        }

    override fun clone(): Call<Result<Response<T?>>> =
        ResultWithResponseCall(proxy.clone(), coroutineScope)

    override fun request(): Request = proxy.request()

    override fun timeout(): Timeout = proxy.timeout()

    override fun isExecuted(): Boolean = proxy.isExecuted

    override fun isCanceled(): Boolean = proxy.isCanceled

    override fun cancel() = proxy.cancel()

    @Suppress("UNCHECKED_CAST")
    private suspend fun <T> Response<T?>.toResult(): Result<Response<T?>> {
        return executeSafely {
            if (isSuccessful) {
                this
            } else {
                throw HttpException(this)
            }
        }
    }
}
