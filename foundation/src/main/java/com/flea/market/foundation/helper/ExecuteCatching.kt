package com.flea.market.foundation.helper

import com.flea.market.foundation.model.Result
import kotlin.coroutines.cancellation.CancellationException

@Suppress("TooGenericExceptionCaught")
public inline fun <T> executeCatching(block: () -> T): Result<T, Throwable> = try {
    Result.success(block())
} catch (e: CancellationException) {
    throw e
} catch (e: Exception) {
    Result.failure(e)
}
