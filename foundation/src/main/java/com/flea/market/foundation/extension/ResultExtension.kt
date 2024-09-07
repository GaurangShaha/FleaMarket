package com.flea.market.foundation.extension

import com.flea.market.foundation.model.Result

public inline fun <S, E : Throwable> Result<S, E>.fold(onSuccess: (S) -> Unit, onError: (E) -> Unit) {
    when (this) {
        is Result.Success -> onSuccess(value)
        is Result.Failure -> onError(this.error)
    }
}

public inline fun <S, V, E : Throwable> Result<S, E>.map(
    onSuccess: (S) -> V
): Result<V, E> {
    return when (this) {
        is Result.Success -> Result.success(onSuccess(value))
        is Result.Failure -> this
    }
}

public inline fun <S, V : Throwable, E : Throwable> Result<S, E>.mapError(
    onError: (E) -> V
): Result<S, V> {
    return when (this) {
        is Result.Success -> this
        is Result.Failure -> Result.failure(onError(error))
    }
}

public inline fun <S, E : Throwable> Result<S, E>.onSuccess(onSuccess: (S) -> Unit) {
    if (this is Result.Success) onSuccess(value)
}

public inline fun <S, E : Throwable> Result<S, E>.onFailure(onFailure: (E) -> Unit) {
    if (this is Result.Failure) onFailure(error)
}

public fun <S, E : Throwable> Result<S, E>.getOrNull(): S? {
    return when (this) {
        is Result.Success -> value
        is Result.Failure -> null
    }
}

public fun <S, E : Throwable> Result<S, E>.getOrElse(defaultValue: S): S {
    return when (this) {
        is Result.Success -> value
        is Result.Failure -> defaultValue
    }
}

public fun <S, E : Throwable> Result<S, E>.getOrThrow(): S {
    return when (this) {
        is Result.Success -> value
        is Result.Failure -> throw this.error
    }
}
