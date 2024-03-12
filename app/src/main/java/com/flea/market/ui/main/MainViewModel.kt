package com.flea.market.ui.main

import com.flea.market.common.base.viewmodel.BaseViewModel
import com.flea.market.ui.main.MainIntent.UpdateSelectedNavigationItemIndex

internal class MainViewModel : BaseViewModel<MainIntent, MainState>(MainState(0)) {
    override fun onHandleIntent(intent: MainIntent) {
        when (intent) {
            is UpdateSelectedNavigationItemIndex -> updateUiState(MainState(intent.index))
        }
    }
}
