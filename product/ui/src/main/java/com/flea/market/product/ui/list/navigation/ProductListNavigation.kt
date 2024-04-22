package com.flea.market.product.ui.list.navigation

import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.flea.market.product.ui.list.ProductListScreen
import com.flea.market.product.ui.list.ProductListViewModel
import org.koin.androidx.compose.koinViewModel

const val PRODUCT_LIST_ROUTE = "product_list"

internal fun NavGraphBuilder.productListScreen() {
    composable(route = PRODUCT_LIST_ROUTE) {
        val productListViewModel = koinViewModel<ProductListViewModel>()
        val uiState by productListViewModel.uiState.collectAsStateWithLifecycle()

        ProductListScreen(
            state = uiState,
            onHandleIntent = productListViewModel::onHandleIntent
        )
    }
}

internal fun NavController.navigateToProductListScreen() {
    navigate(PRODUCT_LIST_ROUTE)
}
