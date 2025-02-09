package com.flea.market.foundation.model

public sealed class Result<out S> {
    public data class Success<out S>(val value: S) : Result<S>()
    public data class Failure(val error: Throwable) : Result<Nothing>()

    public val isSuccess: Boolean get() = this is Success<S>
    public val isFailure: Boolean get() = this is Failure
}
