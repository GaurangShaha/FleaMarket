package com.flea.market.foundation.helper

import com.flea.market.foundation.model.Result
import com.flea.market.foundation.model.Result.Failure
import com.flea.market.foundation.model.Result.Success
import kotlinx.coroutines.ensureActive
import kotlin.coroutines.coroutineContext

@Suppress("TooGenericExceptionCaught")
public suspend inline fun <T> executeSafely(finally: () -> Unit = {}, block: () -> T): Result<T> =
    try {
        Success(block())
    } catch (e: Exception) {
        coroutineContext.ensureActive()
        Failure(e)
    } finally {
        finally()
    }
