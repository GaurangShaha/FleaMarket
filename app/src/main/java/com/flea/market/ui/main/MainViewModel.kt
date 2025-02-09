package com.flea.market.ui.main

import androidx.lifecycle.ViewModel
import com.flea.market.common.contract.viewmodel.ViewModelContract
import com.flea.market.ui.main.MainIntent.ResetSnackbarDetails
import com.flea.market.ui.main.MainIntent.ShowSnackbar
import com.flea.market.ui.main.MainIntent.UpdateSelectedNavigationItemIndex
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

internal class MainViewModel : ViewModelContract<MainState, MainIntent>, ViewModel() {

    private val _uiState: MutableStateFlow<MainState> = MutableStateFlow(
        MainState(selectedNavigationItemIndex = 0, snackbarDetails = null)
    )
    override val uiState: StateFlow<MainState> = _uiState.asStateFlow()

    override fun onHandleIntent(intent: MainIntent) {
        when (intent) {
            is UpdateSelectedNavigationItemIndex ->
                _uiState.value = _uiState.value.copy(selectedNavigationItemIndex = intent.index)

            is ShowSnackbar ->
                _uiState.value = _uiState.value.copy(snackbarDetails = intent.snackbarDetails)

            ResetSnackbarDetails ->
                _uiState.value = _uiState.value.copy(snackbarDetails = null)
        }
    }
}
