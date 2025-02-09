package com.flea.market.favorite.ui.list

import com.flea.market.favorite.ui.list.entity.FavouriteItemViewEntity
import com.flea.market.ui.component.snackbar.model.SnackbarWithActionDetails

internal sealed class FavouriteListUiState {
    object Loading : FavouriteListUiState()
    data class Content(
        val favouriteProductList: List<FavouriteItemViewEntity>,
        val snackbarDetails: SnackbarWithActionDetails?
    ) : FavouriteListUiState()

    object Empty : FavouriteListUiState()
    class Error(val throwable: Throwable) : FavouriteListUiState()
}
