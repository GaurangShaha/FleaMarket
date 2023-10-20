package com.flea.market.ui.main

import com.flea.market.common.base.viewmodel.BaseViewModel
import com.flea.market.ui.main.MainIntent.UpdateSelectedNavigationItemIndex

internal class MainViewModel(
    private val navigationBarVMSlice: NavigationBarVMSlice
) : BaseViewModel<MainIntent, MainState>(MainState(navigationBarVMSlice.selectedNavigationItemIndex)) {
    override fun onHandleIntent(intent: MainIntent) {
        when (intent) {
            is UpdateSelectedNavigationItemIndex -> navigationBarVMSlice.updateSelectedNavigationItemIndex(
                intent.index
            )
        }
    }
}