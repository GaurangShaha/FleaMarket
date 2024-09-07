package com.flea.market.foundation.model

public sealed class Result<out S, out E : Throwable> {
    public data class Success<out S>(val value: S) : Result<S, Nothing>()
    public data class Failure<out E : Throwable>(val error: E) : Result<Nothing, E>()

    public val isSuccess: Boolean get() = this is Success<S>
    public val isFailure: Boolean get() = this is Failure<E>

    public companion object {
        public fun <S> success(value: S): Success<S> = Success(value)
        public fun <E : Throwable> failure(error: E): Failure<E> = Failure(error)
    }
}
