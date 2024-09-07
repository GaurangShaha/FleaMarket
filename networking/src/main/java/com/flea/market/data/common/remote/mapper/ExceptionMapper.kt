package com.flea.market.data.common.remote.mapper

import com.flea.market.foundation.model.InternetConnectionException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

internal fun Exception.toInternetConnectionExceptionOrSelf() = when (this) {
    is UnknownHostException, is SocketTimeoutException -> InternetConnectionException
    else -> this
}
