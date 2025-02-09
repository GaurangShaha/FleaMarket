package com.flea.market.foundation.extension

import com.flea.market.foundation.model.Result
import com.flea.market.foundation.model.Result.Failure
import com.flea.market.foundation.model.Result.Success

public inline fun <S> Result<S>.fold(onSuccess: (S) -> Unit, onError: (Throwable) -> Unit) {
    when (this) {
        is Success -> onSuccess(value)
        is Failure -> onError(this.error)
    }
}

public inline fun <S, V> Result<S>.map(
    onSuccess: (S) -> V
): Result<V> {
    return when (this) {
        is Success -> Success(onSuccess(value))
        is Failure -> this
    }
}

public inline fun <S, T> Result<S>.flatMap(function: (S) -> Result<T>): Result<T> {
    return when (this) {
        is Success -> function(value)
        is Failure -> this
    }
}

public inline fun <S> Result<S>.mapError(
    onError: (Throwable) -> Throwable
): Result<S> {
    return when (this) {
        is Success -> this
        is Failure -> Failure(onError(error))
    }
}

public inline fun <S> Result<S>.onSuccess(onSuccess: (S) -> Unit) {
    if (this is Success) onSuccess(value)
}

public inline fun Result<*>.onFailure(onFailure: (Throwable) -> Unit) {
    if (this is Failure) onFailure(error)
}

public fun <S> Result<S>.getOrNull(): S? {
    return when (this) {
        is Success -> value
        is Failure -> null
    }
}

public fun <S> Result<S>.getOrElse(defaultValue: S): S {
    return when (this) {
        is Success -> value
        is Failure -> defaultValue
    }
}

public fun <S> Result<S>.getOrThrow(): S {
    return when (this) {
        is Success -> value
        is Failure -> throw this.error
    }
}
