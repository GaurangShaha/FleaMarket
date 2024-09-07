package com.flea.market.favorite.ui.list.navigation

import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.flea.market.favorite.ui.list.FavouriteListScreen
import com.flea.market.favorite.ui.list.FavouriteListViewModel
import org.koin.androidx.compose.koinViewModel

const val FAVOURITE_LIST_ROUTE = "favourite_list"

internal fun NavGraphBuilder.favouriteListScreen() {
    composable(route = com.flea.market.favorite.ui.list.navigation.FAVOURITE_LIST_ROUTE) {
        val favouriteListViewModel: FavouriteListViewModel = koinViewModel()
        val uiState by favouriteListViewModel.uiState.collectAsStateWithLifecycle()

        FavouriteListScreen(
            uiState = uiState,
            onHandleIntent = favouriteListViewModel::onHandleIntent
        )
    }
}

internal fun NavController.navigateToFavouriteList() {
    navigate(com.flea.market.favorite.ui.list.navigation.FAVOURITE_LIST_ROUTE)
}
