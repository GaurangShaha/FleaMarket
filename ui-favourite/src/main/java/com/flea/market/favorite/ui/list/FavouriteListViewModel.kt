package com.flea.market.favorite.ui.list

import androidx.lifecycle.viewModelScope
import com.flea.market.cart.repository.CartRepository
import com.flea.market.common.base.viewmodel.BaseViewModel
import com.flea.market.common.slice.SnackBarSlice
import com.flea.market.favorite.ui.list.FavouriteListIntent.MoveToCart
import com.flea.market.favorite.ui.list.FavouriteListIntent.RemoveFromFavourite
import com.flea.market.favorite.ui.list.FavouriteListIntent.SnackbarResult
import com.flea.market.favorite.ui.list.FavouriteListUiState.Content
import com.flea.market.favorite.ui.list.FavouriteListUiState.Empty
import com.flea.market.favorite.ui.list.FavouriteListUiState.Error
import com.flea.market.favorite.ui.list.FavouriteListUiState.Loading
import com.flea.market.favorite.ui.list.entity.FavouriteItemViewEntity
import com.flea.market.favorite.ui.list.mapper.toCartProductDetails
import com.flea.market.favorite.ui.list.mapper.toFavouriteItemViewEntity
import com.flea.market.favourite.repository.FavouriteRepository
import com.flea.market.foundation.extension.onSuccess
import com.flea.market.ui.component.SnackBarDetails
import com.flea.market.ui.component.SnackbarDelegate.SnackbarType.SUCCESS
import com.flea.market.ui.favourite.R.string
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

private const val DELAY_FOR_SIMULATING_LOADING = 1000L

internal class FavouriteListViewModel(
    private val favouriteRepository: FavouriteRepository,
    private val cartRepository: CartRepository,
    private val snackBarSlice: SnackBarSlice
) : BaseViewModel<FavouriteListIntent, FavouriteListUiState>(Loading) {
    init {
        favouriteRepository.getFavouriteProductsStream().map { it.toFavouriteItemViewEntity() }
            .map {
                if (it.isNotEmpty()) {
                    Content(it, snackBarSlice.snackbarUiState)
                } else {
                    Empty
                }
            }.catch { emit(Error(it)) }.onStart { delay(DELAY_FOR_SIMULATING_LOADING) }
            .collectAndUpdateUiState()
    }

    override fun onHandleIntent(intent: FavouriteListIntent) {
        when (intent) {
            is MoveToCart -> moveToCart(intent.favouriteItemViewEntity)
            is RemoveFromFavourite -> removeFromFavourite(intent.productId)
            is SnackbarResult -> snackBarSlice.onSnackbarResult(intent.isActionPerformed)
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
                        message = string.move_to_cart_success,
                        snackbarType = SUCCESS
                    )

                    snackBarSlice.showSnackBar(snackBarDetails)
                    favouriteRepository.removeFromFavourite(favouriteItemViewEntity.id)
                }
        }
    }
}
