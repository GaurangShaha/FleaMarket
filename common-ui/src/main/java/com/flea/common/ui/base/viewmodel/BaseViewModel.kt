package com.flea.common.ui.base.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel<I, S>(initialUiState: S) : ViewModel() {
    private val _uiState: MutableStateFlow<S> = MutableStateFlow(initialUiState)
    open val uiState: StateFlow<S> = _uiState.asStateFlow()

    abstract fun handleIntent(intent: I)

    protected fun updateUiState(uiState: S) {
        _uiState.value = uiState
    }

    fun Flow<S>.collectAndUpdateUiState(scope: CoroutineScope = viewModelScope) {
        scope.launch {
            collect { updateUiState(it) }
        }
    }
}