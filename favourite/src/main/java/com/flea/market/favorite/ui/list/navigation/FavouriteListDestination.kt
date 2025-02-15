package com.flea.market.favorite.ui.list.navigation

import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.flea.market.favorite.ui.list.FavouriteListScreen
import com.flea.market.favorite.ui.list.FavouriteListViewModel
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel

@Serializable
public object FavouriteListDestination

internal fun NavGraphBuilder.favouriteListScreen() {
    composable<FavouriteListDestination> {
        val favouriteListViewModel: FavouriteListViewModel = koinViewModel()
        val uiState by favouriteListViewModel.uiState.collectAsStateWithLifecycle()

        FavouriteListScreen(
            uiState = uiState,
            processIntent = favouriteListViewModel::processIntent
        )
    }
}
