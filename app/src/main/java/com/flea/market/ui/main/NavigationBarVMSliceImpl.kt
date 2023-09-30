package com.flea.market.ui.main

import com.flea.common.ui.base.viewmodel.BaseViewModelSlice

internal class NavigationBarVMSliceImpl : BaseViewModelSlice<Int>(0),
    NavigationBarVMSlice {
    override val selectedNavigationItemIndex = sliceUiState

    override fun updateSelectedNavigationItemIndex(index: Int) = updateSliceUiState(index)
}