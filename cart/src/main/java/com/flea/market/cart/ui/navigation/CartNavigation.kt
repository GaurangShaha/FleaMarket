package com.flea.market.cart.ui.navigation

import androidx.navigation.NavGraphBuilder
import com.flea.market.cart.ui.details.navigation.cartDetailsScreen

public fun NavGraphBuilder.addCartGraph() {
    cartDetailsScreen()
}
