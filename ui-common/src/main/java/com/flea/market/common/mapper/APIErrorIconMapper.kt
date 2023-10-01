package com.flea.market.common.mapper

import com.flea.market.foundation.model.NetworkException

fun Throwable.toAPIErrorIcon() = if (this is NetworkException) {
    com.flea.market.component.R.drawable.ic_network_error
} else {
    com.flea.market.component.R.drawable.ic_server_error
}