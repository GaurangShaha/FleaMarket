package com.flea.market.common.extension

import kotlinx.coroutines.flow.StateFlow

public inline fun <reified S> StateFlow<*>.ifInstanceOf(block: (S) -> Unit) {
    val currentState = value
    if (currentState is S) {
        block(currentState)
    }
}
