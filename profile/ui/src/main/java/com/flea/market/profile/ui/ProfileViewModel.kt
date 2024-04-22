package com.flea.market.profile.ui

import com.flea.market.common.base.viewmodel.BaseViewModel

class ProfileViewModel : BaseViewModel<ProfileIntent, ProfileUiState>(ProfileUiState()) {
    override fun onHandleIntent(intent: ProfileIntent) {
        // Do nothing
    }
}
