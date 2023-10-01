package com.flea.favourite.ui.list.navigation

import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.flea.favourite.ui.list.FavouriteListScreen
import com.flea.favourite.ui.list.FavouriteListViewModel
import org.koin.androidx.compose.koinViewModel

const val FAVOURITE_LIST_ROUTE = "favourite_list"

internal fun NavGraphBuilder.favouriteListScreen() {
    composable(route = FAVOURITE_LIST_ROUTE) {
        val favouriteListViewModel: FavouriteListViewModel = koinViewModel()
        val uiState by favouriteListViewModel.uiState.collectAsStateWithLifecycle()

        FavouriteListScreen(
            uiState = uiState,
            handleIntent = favouriteListViewModel::handleIntent,
            notifySnackbarResult = favouriteListViewModel::notifySnackbarResult
        )
    }
}

internal fun NavController.navigateToFavouriteList() {
    navigate(FAVOURITE_LIST_ROUTE)
}