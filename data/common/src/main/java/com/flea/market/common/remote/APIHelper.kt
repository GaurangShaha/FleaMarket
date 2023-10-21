package com.flea.market.common.remote

import android.os.RemoteException
import com.flea.market.foundation.model.NetworkException
import com.flea.market.foundation.model.Result
import okhttp3.Headers
import okio.BufferedSource
import retrofit2.Response
import java.net.SocketTimeoutException
import java.net.UnknownHostException

@Suppress("UNCHECKED_CAST", "TooGenericExceptionCaught", "InstanceOfCheckForException")
inline fun <E, M> request(
    onSuccess: (
        httpCode: Int,
        headers: Headers,
        responseEntity: E
    ) -> M = { _, _, responseEntity -> responseEntity as M },
    onError: (
        errorCode: Int,
        headers: Headers,
        errorBodyBuffer: BufferedSource?
    ) -> Throwable = { _, _, _ -> RemoteException() },
    call: () -> Response<E>
): Result<M, Throwable> = try {
    val response = call()
    if (response.isSuccessful) {
        Result.success(onSuccess(response.code(), response.headers(), response.body() as E))
    } else {
        Result.failure(
            onError(response.code(), response.headers(), response.errorBody()?.source())
        )
    }
} catch (exception: Exception) {
    Result.failure(
        if (exception is UnknownHostException || exception is SocketTimeoutException) {
            NetworkException
        } else {
            exception
        }
    )
}
