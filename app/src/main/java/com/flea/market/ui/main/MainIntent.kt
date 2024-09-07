package com.flea.market.ui.main

internal sealed class MainIntent {
    class UpdateSelectedNavigationItemIndex(val index: Int) : MainIntent()
}
