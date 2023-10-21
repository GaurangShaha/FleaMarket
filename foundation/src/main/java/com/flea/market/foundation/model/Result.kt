package com.flea.market.foundation.model

sealed class Result<out S, out E : Throwable> {
    data class Success<out S>(val value: S) : Result<S, Nothing>()
    data class Failure<out E : Throwable>(val error: E) : Result<Nothing, E>()

    val isSuccess get() = this is Success<S>
    val isFailure get() = this is Failure<E>

    companion object {
        fun <S> success(value: S) = Success(value)
        fun <E : Throwable> failure(error: E) = Failure(error)
    }
}
