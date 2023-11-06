package com.flea.market.ui.main

import com.flea.market.common.base.viewmodel.BaseViewModelSlice

internal class NavigationBarVMSlice :
    BaseViewModelSlice<Int>(0) {
    val selectedNavigationItemIndex = sliceUiState

    fun updateSelectedNavigationItemIndex(index: Int) = updateSliceUiState(index)
}
