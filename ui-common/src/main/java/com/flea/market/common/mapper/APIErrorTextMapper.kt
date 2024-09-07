package com.flea.market.common.mapper

import com.flea.market.foundation.model.InternetConnectionException
import com.flea.market.ui.common.R

public fun Throwable.toAPIErrorMessage(): Int = if (this is InternetConnectionException) {
    R.string.network_error
} else {
    R.string.server_error
}
