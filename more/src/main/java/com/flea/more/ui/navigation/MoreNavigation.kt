package com.flea.more.ui.navigation

import androidx.navigation.NavGraphBuilder
import com.flea.common.ui.app.state.FleaMarketAppState
import com.flea.more.ui.dummy.navigation.dummyFavouriteScreen

fun NavGraphBuilder.addMoreGraph(appState: FleaMarketAppState) {
    dummyFavouriteScreen()
}