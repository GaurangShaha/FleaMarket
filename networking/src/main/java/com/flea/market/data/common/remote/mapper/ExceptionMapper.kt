package com.flea.market.data.common.remote.mapper

import com.flea.market.foundation.model.InternetDisconnectionException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

internal fun Exception.toInternetConnectionExceptionOrSelf() = when (this) {
    is UnknownHostException, is SocketTimeoutException -> InternetDisconnectionException
    else -> this
}
