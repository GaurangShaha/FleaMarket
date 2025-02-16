package com.flea.market.ui.main

import android.artisan.ui.common.contract.viewmodel.ViewModelContract
import androidx.lifecycle.ViewModel
import com.flea.market.ui.main.MainIntent.ResetSnackbarDetails
import com.flea.market.ui.main.MainIntent.ShowSnackbar
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

internal class MainViewModel : ViewModelContract<MainState, MainIntent>, ViewModel() {
    private val _uiState: MutableStateFlow<MainState> = MutableStateFlow(MainState())
    override val uiState: StateFlow<MainState> = _uiState.asStateFlow()

    override fun processIntent(intent: MainIntent) {
        when (intent) {
            is ShowSnackbar ->
                _uiState.value = _uiState.value.copy(snackbarDetails = intent.snackbarDetails)

            ResetSnackbarDetails ->
                _uiState.value = _uiState.value.copy(snackbarDetails = null)
        }
    }
}
