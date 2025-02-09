package com.flea.market.product.ui.list.navigation

import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.flea.market.product.ui.list.ProductListScreen
import com.flea.market.product.ui.list.ProductListViewModel
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel

@Serializable
public object ProductListDestination

internal fun NavGraphBuilder.productListScreen() {
    composable<ProductListDestination> {
        val productListViewModel = koinViewModel<ProductListViewModel>()
        val uiState by productListViewModel.uiState.collectAsStateWithLifecycle()

        ProductListScreen(
            state = uiState,
            onHandleIntent = productListViewModel::onHandleIntent
        )
    }
}
