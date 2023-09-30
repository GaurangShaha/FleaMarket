package com.flea.market.ui.main

sealed class MainIntent {
    class UpdateSelectedNavigationItemIndex(val index: Int) : MainIntent()
}
