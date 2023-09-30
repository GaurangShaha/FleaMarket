package com.flea.cart.ui.details.navigation

import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.flea.cart.ui.details.CartDetailsScreen
import com.flea.cart.ui.details.CartDetailsViewModel
import com.flea.common.ui.app.state.FleaMarketAppState
import com.flea.common.ui.navigation.ProductDetailsDeepLink
import org.koin.androidx.compose.koinViewModel

const val CART_DETAILS_ROUTE = "cart_details"

internal fun NavGraphBuilder.cartDetailsScreen(appState: FleaMarketAppState) {
    composable(route = CART_DETAILS_ROUTE) {
        val cartDetailsViewModel: CartDetailsViewModel = koinViewModel()
        val uiState by cartDetailsViewModel.uiState.collectAsStateWithLifecycle()

        CartDetailsScreen(uiState = uiState,
            handleIntent = cartDetailsViewModel::handleIntent,
            navigateToProductDetails = {
                appState.navController.navigate(ProductDetailsDeepLink.getUri(it))
            })
    }
}

internal fun NavController.navigateToCart() {
    navigate(CART_DETAILS_ROUTE)
}