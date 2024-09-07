package com.flea.market.favorite.ui.list

import com.flea.market.favorite.ui.list.entity.FavouriteItemViewEntity
import com.flea.market.ui.component.SnackbarDetails

internal sealed class FavouriteListUiState {
    object Loading : FavouriteListUiState()
    data class Content(
        val favouriteProductList: List<FavouriteItemViewEntity>,
        val snackbarDetails: SnackbarDetails?
    ) : FavouriteListUiState()

    object Empty : FavouriteListUiState()
    class Error(val throwable: Throwable) : FavouriteListUiState()
}
