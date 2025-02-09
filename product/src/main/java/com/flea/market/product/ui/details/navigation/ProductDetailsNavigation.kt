package com.flea.market.product.ui.details.navigation

import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.flea.market.common.navigation.ProductDetailsDestination
import com.flea.market.product.ui.details.ProductDetailsScreen
import com.flea.market.product.ui.details.ProductDetailsViewModel
import org.koin.androidx.compose.koinViewModel

internal fun NavGraphBuilder.productDetailsScreen() {
    composable<ProductDetailsDestination> {
        val productDetailsViewModel: ProductDetailsViewModel = koinViewModel()
        val uiState by productDetailsViewModel.uiState.collectAsStateWithLifecycle()

        ProductDetailsScreen(
            uiState = uiState,
            onHandleIntent = productDetailsViewModel::onHandleIntent
        )
    }
}
