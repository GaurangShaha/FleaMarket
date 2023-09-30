package com.flea.favourite.ui.list.navigation

import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.flea.common.ui.app.state.FleaMarketAppState
import com.flea.common.ui.navigation.ProductDetailsDeepLink
import com.flea.favourite.ui.list.FavouriteListScreen
import com.flea.favourite.ui.list.FavouriteListViewModel
import org.koin.androidx.compose.koinViewModel

const val FAVOURITE_LIST_ROUTE = "favourite_list"

internal fun NavGraphBuilder.favouriteListScreen(appState: FleaMarketAppState) {
    composable(route = FAVOURITE_LIST_ROUTE) {
        val favouriteListViewModel: FavouriteListViewModel = koinViewModel()
        val uiState by favouriteListViewModel.uiState.collectAsStateWithLifecycle()

        FavouriteListScreen(
            uiState = uiState,
            handleIntent = favouriteListViewModel::handleIntent,
            navigateToProductDetails = {
                appState.navController.navigate(ProductDetailsDeepLink.getUri(it))
            },
            showSnackbar = { message, action, type ->
                appState.showSnackbar(message, action, type)
            },
            notifySnackbarResult = favouriteListViewModel::notifySnackbarResult
        )
    }
}

internal fun NavController.navigateToFavouriteList() {
    navigate(FAVOURITE_LIST_ROUTE)
}