package com.flea.market.common.mapper

import com.flea.common.ui.R
import com.flea.market.foundation.model.NetworkException

fun Throwable.toAPIErrorMessage() = if (this is NetworkException) {
    R.string.network_error
} else {
    R.string.server_error
}