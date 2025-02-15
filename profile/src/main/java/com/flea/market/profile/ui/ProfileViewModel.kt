package com.flea.market.profile.ui

import androidx.lifecycle.ViewModel
import com.flea.market.common.contract.viewmodel.ViewModelContract
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

internal class ProfileViewModel : ViewModelContract<ProfileUiState, ProfileIntent>, ViewModel() {
    override val uiState: StateFlow<ProfileUiState> = MutableStateFlow(ProfileUiState())

    override fun processIntent(intent: ProfileIntent) {
        // Do nothing
    }
}
