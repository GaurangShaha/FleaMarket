package com.flea.market.cart.ui.details.navigation

import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.flea.market.cart.ui.details.CartDetailsScreen
import com.flea.market.cart.ui.details.CartDetailsViewModel
import org.koin.androidx.compose.koinViewModel

const val CART_DETAILS_ROUTE = "cart_details"

internal fun NavGraphBuilder.cartDetailsScreen() {
    composable(route = com.flea.market.cart.ui.details.navigation.CART_DETAILS_ROUTE) {
        val cartDetailsViewModel: CartDetailsViewModel = koinViewModel()
        val uiState by cartDetailsViewModel.uiState.collectAsStateWithLifecycle()

        CartDetailsScreen(uiState = uiState, onHandleIntent = cartDetailsViewModel::onHandleIntent)
    }
}

internal fun NavController.navigateToCart() {
    navigate(com.flea.market.cart.ui.details.navigation.CART_DETAILS_ROUTE)
}
