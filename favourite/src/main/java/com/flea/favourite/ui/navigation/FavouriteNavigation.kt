package com.flea.favourite.ui.navigation

import androidx.navigation.NavGraphBuilder
import com.flea.common.ui.app.state.FleaMarketAppState
import com.flea.favourite.ui.list.navigation.favouriteListScreen

fun NavGraphBuilder.addFavouriteGraph(appState: FleaMarketAppState) {
    favouriteListScreen(appState)
}