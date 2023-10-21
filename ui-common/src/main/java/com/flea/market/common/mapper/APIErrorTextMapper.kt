package com.flea.market.common.mapper

import com.flea.market.foundation.model.NetworkException
import com.flea.market.ui.common.R

fun Throwable.toAPIErrorMessage() = if (this is NetworkException) {
    R.string.network_error
} else {
    R.string.server_error
}
