package com.flea.market.cart.ui.details.navigation

import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.flea.market.cart.ui.details.CartDetailsScreen
import com.flea.market.cart.ui.details.CartDetailsViewModel
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel

@Serializable
public object CartDetailsDestination

internal fun NavGraphBuilder.cartDetailsScreen() {
    composable<CartDetailsDestination> {
        val cartDetailsViewModel: CartDetailsViewModel = koinViewModel()
        val uiState by cartDetailsViewModel.uiState.collectAsStateWithLifecycle()

        CartDetailsScreen(uiState = uiState, onHandleIntent = cartDetailsViewModel::onHandleIntent)
    }
}
