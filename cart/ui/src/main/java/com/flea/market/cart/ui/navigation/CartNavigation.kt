package com.flea.market.cart.ui.navigation

import androidx.navigation.NavGraphBuilder
import com.flea.market.cart.ui.details.navigation.cartDetailsScreen

fun NavGraphBuilder.addCartGraph() {
    cartDetailsScreen()
}
