package com.flea.product.ui.navigation

import androidx.navigation.NavGraphBuilder
import com.flea.common.ui.app.state.FleaMarketAppState
import com.flea.product.ui.details.navigation.productDetailsScreen
import com.flea.product.ui.list.navigation.productListScreen

fun NavGraphBuilder.addProductGraph(
    appState: FleaMarketAppState
) {
    productListScreen(appState)
    productDetailsScreen(appState)
}