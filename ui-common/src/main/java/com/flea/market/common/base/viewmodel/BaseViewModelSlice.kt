package com.flea.market.common.base.viewmodel

import androidx.annotation.CallSuper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

open class BaseViewModelSlice<S>(initialUiState: S) {
    private lateinit var viewModelScope: CoroutineScope
    private val _sliceUiState: MutableStateFlow<S> = MutableStateFlow(initialUiState)
    val sliceUiState: StateFlow<S> = _sliceUiState.asStateFlow()

    @CallSuper
    fun attachScope(scope: CoroutineScope) {
        this.viewModelScope = scope
    }

    @CallSuper
    fun updateSliceUiState(state: S) {
        _sliceUiState.value = state
    }
}
