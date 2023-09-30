package com.flea.favourite.ui.list

import androidx.lifecycle.viewModelScope
import com.flea.common.ui.app.state.SnackbarDelegate.SnackbarType
import com.flea.common.ui.base.viewmodel.BaseViewModel
import com.flea.common.ui.slice.SnackBarSlice
import com.flea.common.ui.slice.SnackBarSlice.SnackBarDetails
import com.flea.favourite.R
import com.flea.favourite.ui.list.FavouriteListIntent.MoveToCart
import com.flea.favourite.ui.list.FavouriteListIntent.RemoveFromFavourite
import com.flea.favourite.ui.list.FavouriteListUiState.Content
import com.flea.favourite.ui.list.FavouriteListUiState.Empty
import com.flea.favourite.ui.list.FavouriteListUiState.Error
import com.flea.favourite.ui.list.FavouriteListUiState.Loading
import com.flea.favourite.ui.list.entity.FavouriteItemViewEntity
import com.flea.favourite.ui.list.mapper.toCartProductDetails
import com.flea.favourite.ui.list.mapper.toFavouriteItemViewEntity
import com.flea.market.data.cart.repository.CartRepository
import com.flea.market.data.favourite.repository.FavouriteRepository
import com.flea.market.foundation.extension.onSuccess
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

internal class FavouriteListViewModel(
    private val favouriteRepository: FavouriteRepository,
    private val cartRepository: CartRepository,
    private val snackBarSlice: SnackBarSlice
) : BaseViewModel<FavouriteListIntent, FavouriteListUiState>(Loading),
    SnackBarSlice by snackBarSlice {
    init {
        favouriteRepository.getFavouriteProducts().map { it.toFavouriteItemViewEntity() }.map {
            if (it.isNotEmpty()) {
                Content(it, snackBarSlice.snackbarUiState)
            } else {
                Empty
            }
        }.catch { emit(Error(it)) }.onStart { /*Added delay to simulate loading*/ delay(1000L) }
            .collectAndUpdateUiState()
    }

    override fun handleIntent(intent: FavouriteListIntent) {
        when (intent) {
            is MoveToCart -> moveToCart(intent.favouriteItemViewEntity)
            is RemoveFromFavourite -> removeFromFavourite(intent.productId)
        }
    }

    private fun removeFromFavourite(productId: Int) {
        viewModelScope.launch {
            favouriteRepository.removeFromFavourite(productId)
        }
    }

    private fun moveToCart(favouriteItemViewEntity: FavouriteItemViewEntity) {
        viewModelScope.launch {
            cartRepository.addOrUpdateItem(favouriteItemViewEntity.toCartProductDetails())
                .onSuccess {
                    val snackBarDetails = SnackBarDetails(
                        message = R.string.move_to_cart_success, snackbarType = SnackbarType.SUCCESS
                    )

                    showSnackBar(snackBarDetails)
                    favouriteRepository.removeFromFavourite(favouriteItemViewEntity.id)
                }
        }
    }
}