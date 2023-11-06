package com.flea.market.favorite.ui.list

import com.flea.market.favorite.ui.list.entity.FavouriteItemViewEntity
import com.flea.market.ui.component.SnackBarDetails
import kotlinx.coroutines.flow.StateFlow

internal sealed class FavouriteListUiState {
    object Loading : FavouriteListUiState()
    data class Content(
        val favouriteProductList: List<FavouriteItemViewEntity>,
        val snackbarUiState: StateFlow<SnackBarDetails?>
    ) : FavouriteListUiState()

    object Empty : FavouriteListUiState()
    class Error(val throwable: Throwable) : FavouriteListUiState()
}
