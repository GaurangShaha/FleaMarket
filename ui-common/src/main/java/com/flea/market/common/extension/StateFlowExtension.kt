package com.flea.market.common.extension

import kotlinx.coroutines.flow.StateFlow

inline fun <reified S> StateFlow<*>.ifInstanceOf(block: (S) -> Unit) {
    val currentState = value
    if (currentState is S) {
        block(currentState)
    }
}