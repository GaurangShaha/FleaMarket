package com.flea.market.product.ui.details.navigation

import androidx.compose.runtime.getValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.flea.market.common.navigation.ProductDetailsDeepLink
import com.flea.market.common.navigation.ProductDetailsDeepLink.PRODUCT_DETAILS_DEEPLINK_ARGUMENT_PRODUCT_ID
import com.flea.market.product.ui.details.ProductDetailsScreen
import com.flea.market.product.ui.details.ProductDetailsViewModel
import org.koin.androidx.compose.koinViewModel

private const val PRODUCT_DETAILS_ARGUMENT_PRODUCT_ID = PRODUCT_DETAILS_DEEPLINK_ARGUMENT_PRODUCT_ID
private const val PRODUCT_DETAILS_ROUTE_BY_ID = "product_details/%d"
private const val PRODUCT_DETAILS_ROUTE =
    "product_details/{$PRODUCT_DETAILS_DEEPLINK_ARGUMENT_PRODUCT_ID}"

internal fun NavGraphBuilder.productDetailsScreen() {
    composable(
        route = PRODUCT_DETAILS_ROUTE,
        arguments = listOf(navArgument(PRODUCT_DETAILS_ARGUMENT_PRODUCT_ID) {
            type = NavType.IntType
        }),
        deepLinks = listOf(ProductDetailsDeepLink.navDeeplink())
    ) {
        val productDetailsViewModel: ProductDetailsViewModel = koinViewModel()
        val uiState by productDetailsViewModel.uiState.collectAsStateWithLifecycle()

        ProductDetailsScreen(
            uiState = uiState, onHandleIntent = productDetailsViewModel::onHandleIntent
        )
    }
}

internal class ProductDetailsArgs(val productId: Int) {
    constructor(
        savedStateHandle: SavedStateHandle
    ) : this(checkNotNull(savedStateHandle.get<Int>(PRODUCT_DETAILS_ARGUMENT_PRODUCT_ID)))
}

internal fun NavController.navigateToProductDetails(productId: Int) {
    navigate(PRODUCT_DETAILS_ROUTE_BY_ID.format(productId))
}