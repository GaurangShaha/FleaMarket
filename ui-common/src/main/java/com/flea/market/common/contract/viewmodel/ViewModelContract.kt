package com.flea.market.common.contract.viewmodel

import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow

public interface ViewModelContract<S, I> {
    public val uiState: StateFlow<S>

    public fun onHandleIntent(intent: I)

    public companion object {
        public val startWithFiveSecStopTimeout: SharingStarted = SharingStarted.WhileSubscribed(5000)
    }
}