package com.flea.market.foundation.extension

import com.flea.market.foundation.model.Result

inline fun <S, E : Throwable> Result<S, E>.fold(onSuccess: (S) -> Unit, onError: (E) -> Unit) {
    when (this) {
        is Result.Success -> onSuccess(value)
        is Result.Failure -> onError(this.error)
    }
}

inline fun <S, V, E : Throwable> Result<S, E>.map(
    onSuccess: (S) -> Result<V, E>
): Result<V, E> {
    return when (this) {
        is Result.Success -> onSuccess(value)
        is Result.Failure -> this
    }
}

inline fun <S, V : Throwable, E : Throwable> Result<S, E>.mapError(
    onError: (E) -> Result<S, V>
): Result<S, V> {
    return when (this) {
        is Result.Success -> this
        is Result.Failure -> onError(error)
    }
}

inline fun <S, E : Throwable> Result<S, E>.onSuccess(onSuccess: (S) -> Unit) {
    if (this is Result.Success) onSuccess(value)
}

inline fun <S, E : Throwable> Result<S, E>.onFailure(onFailure: (E) -> Unit) {
    if (this is Result.Failure) onFailure(error)
}

fun <S, E : Throwable> Result<S, E>.getOrNull(): S? {
    return when (this) {
        is Result.Success -> value
        is Result.Failure -> null
    }
}

fun <S, E : Throwable> Result<S, E>.getOrElse(defaultValue: S): S {
    return when (this) {
        is Result.Success -> value
        is Result.Failure -> defaultValue
    }
}