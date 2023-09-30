package com.flea.common.ui.mapper

import com.flea.common.ui.R
import com.flea.market.foundation.model.NetworkException

fun Throwable.toAPIErrorIcon() = if (this is NetworkException) {
    R.drawable.ic_network_error
} else {
    R.drawable.ic_server_error
}