package com.flea.cart.ui.navigation

import androidx.navigation.NavGraphBuilder
import com.flea.cart.ui.details.navigation.cartDetailsScreen

fun NavGraphBuilder.addCartGraph() {
    cartDetailsScreen()
}