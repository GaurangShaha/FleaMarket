package com.flea.common.ui.base.viewmodel

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

abstract class BaseViewModelSlice<S>(initialUiState: S) {
    protected lateinit var viewModelScope: CoroutineScope
    private val _sliceUiState: MutableStateFlow<S> = MutableStateFlow(initialUiState)
    val sliceUiState: StateFlow<S> = _sliceUiState.asStateFlow()

    fun attachScope(scope: CoroutineScope) {
        this.viewModelScope = scope
    }

    fun updateSliceUiState(state: S) {
        _sliceUiState.value = state
    }
}