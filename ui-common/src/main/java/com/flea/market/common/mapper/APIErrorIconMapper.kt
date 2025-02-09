package com.flea.market.common.mapper

import com.flea.market.foundation.model.InternetDisconnectionException

public fun Throwable.toAPIErrorIcon(): Int = if (this is InternetDisconnectionException) {
    com.flea.market.ui.component.R.drawable.ic_network_error
} else {
    com.flea.market.ui.component.R.drawable.ic_server_error
}
