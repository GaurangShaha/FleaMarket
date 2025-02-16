package com.flea.market.profile.ui

import android.artisan.ui.common.contract.viewmodel.ViewModelContract
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

internal class ProfileViewModel : ViewModelContract<ProfileUiState, ProfileIntent>, ViewModel() {
    override val uiState: StateFlow<ProfileUiState> = MutableStateFlow(ProfileUiState())

    override fun processIntent(intent: ProfileIntent) {
        // Do nothing
    }
}
