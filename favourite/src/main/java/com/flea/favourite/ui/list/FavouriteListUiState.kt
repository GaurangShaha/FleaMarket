package com.flea.favourite.ui.list

import com.flea.common.ui.slice.SnackBarSlice
import com.flea.favourite.ui.list.entity.FavouriteItemViewEntity
import kotlinx.coroutines.flow.StateFlow

internal sealed class FavouriteListUiState {
    object Loading : FavouriteListUiState()
    data class Content(
        val favouriteProductList: List<FavouriteItemViewEntity>,
        val snackbarUiState: StateFlow<SnackBarSlice.SnackBarDetails?>
    ) : FavouriteListUiState()

    object Empty : FavouriteListUiState()
    class Error(val throwable: Throwable) : FavouriteListUiState()
}