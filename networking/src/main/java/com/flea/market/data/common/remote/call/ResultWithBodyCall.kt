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
import java.lang.reflect.Type

@Suppress("TooGenericExceptionCaught")
internal class ResultWithBodyCall<T : Any>(
    private val proxy: Call<T>,
    private val resultRawType: Type,
    private val coroutineScope: CoroutineScope,
) : Call<Result<T?, Throwable>> {

    override fun enqueue(callback: Callback<Result<T?, Throwable>>) {
        coroutineScope.launch {
            try {
                val response = proxy.awaitResponse()
                callback.onResponse(
                    this@ResultWithBodyCall,
                    Response.success(response.toResult(resultRawType))
                )
            } catch (e: Exception) {
                coroutineContext.ensureActive()
                callback.onResponse(
                    this@ResultWithBodyCall,
                    Response.success(Result.failure(e.toInternetConnectionExceptionOrSelf()))
                )
            }
        }
    }

    override fun execute(): Response<Result<T?, Throwable>> =
        runBlocking(coroutineScope.coroutineContext) {
            try {
                Response.success(proxy.execute().toResult(resultRawType))
            } catch (e: Exception) {
                coroutineContext.ensureActive()
                Response.success(Result.failure(e.toInternetConnectionExceptionOrSelf()))
            }
        }

    override fun clone(): Call<Result<T?, Throwable>> =
        ResultWithBodyCall(proxy.clone(), resultRawType, coroutineScope)

    override fun request(): Request = proxy.request()

    override fun timeout(): Timeout = proxy.timeout()

    override fun isExecuted(): Boolean = proxy.isExecuted

    override fun isCanceled(): Boolean = proxy.isCanceled

    override fun cancel() = proxy.cancel()

    @Suppress("UNCHECKED_CAST")
    private fun <T : Any?> Response<T>.toResult(resultRawType: Type): Result<T?, Throwable> {
        return executeCatching {
            if (isSuccessful) {
                if (resultRawType == Unit::class.java) {
                    Unit as T
                } else {
                    body()
                }
            } else {
                throw HttpException(this)
            }
        }
    }
}
