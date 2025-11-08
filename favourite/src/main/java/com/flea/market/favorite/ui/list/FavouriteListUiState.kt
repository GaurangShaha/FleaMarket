package com.flea.market.favorite.ui.list

import building.blocks.ui.component.snackbar.model.SnackbarWithActionDetails
import com.flea.market.favorite.ui.list.entity.FavouriteItemViewEntity

internal sealed class FavouriteListUiState {
    object Loading : FavouriteListUiState()
    data class Content(
        val favouriteProductList: List<FavouriteItemViewEntity>,
        val snackbarDetails: SnackbarWithActionDetails?
    ) : FavouriteListUiState()

    object Empty : FavouriteListUiState()
    class Error(val throwable: Throwable) : FavouriteListUiState()
}
