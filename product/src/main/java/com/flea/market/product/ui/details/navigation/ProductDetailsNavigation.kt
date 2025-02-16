package com.flea.market.product.ui.details.navigation

import android.artisan.ui.common.navigation.ProductDetailsDestination
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.flea.market.product.ui.details.ProductDetailsScreen
import com.flea.market.product.ui.details.ProductDetailsViewModel
import org.koin.androidx.compose.koinViewModel

internal fun NavGraphBuilder.productDetailsScreen() {
    composable<ProductDetailsDestination>(
        enterTransition = { slideInHorizontally(initialOffsetX = { it }) },
        exitTransition = { slideOutHorizontally(targetOffsetX = { it }) }
    ) {
        val productDetailsViewModel: ProductDetailsViewModel = koinViewModel()
        val uiState by productDetailsViewModel.uiState.collectAsStateWithLifecycle()

        ProductDetailsScreen(
            uiState = uiState,
            processIntent = productDetailsViewModel::processIntent
        )
    }
}
