package com.flea.market.cart.ui.details.navigation

import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.flea.market.cart.ui.details.CartDetailsScreen
import com.flea.market.cart.ui.details.CartDetailsViewModel
import org.koin.androidx.compose.koinViewModel

public const val CART_DETAILS_ROUTE: String = "cart_details"

internal fun NavGraphBuilder.cartDetailsScreen() {
    composable(route = CART_DETAILS_ROUTE) {
        val cartDetailsViewModel: CartDetailsViewModel = koinViewModel()
        val uiState by cartDetailsViewModel.uiState.collectAsStateWithLifecycle()

        CartDetailsScreen(uiState = uiState, onHandleIntent = cartDetailsViewModel::onHandleIntent)
    }
}

internal fun NavController.navigateToCart() {
    navigate(CART_DETAILS_ROUTE)
}
