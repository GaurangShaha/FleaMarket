package com.flea.cart.ui.navigation

import androidx.navigation.NavGraphBuilder
import com.flea.cart.ui.details.navigation.cartDetailsScreen
import com.flea.common.ui.app.state.FleaMarketAppState

fun NavGraphBuilder.addCartGraph(appState: FleaMarketAppState) {
    cartDetailsScreen(appState)
}