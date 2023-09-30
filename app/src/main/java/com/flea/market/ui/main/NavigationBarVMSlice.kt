package com.flea.market.ui.main

import kotlinx.coroutines.flow.StateFlow

internal interface NavigationBarVMSlice {
    val selectedNavigationItemIndex: StateFlow<Int>
    fun updateSelectedNavigationItemIndex(index: Int)
}