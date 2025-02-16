package com.flea.market.favorite.ui.list

import android.artisan.foundation.extension.onSuccess
import android.artisan.ui.common.contract.viewmodel.ViewModelContract
import android.artisan.ui.common.extension.ifInstanceOf
import android.artisan.ui.component.snackbar.SnackbarDelegate.SnackbarType.SUCCESS
import android.artisan.ui.component.snackbar.model.SnackbarWithActionDetails
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.flea.market.cart.data.repository.CartRepository
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
import com.flea.market.favorite.ui.list.mapper.toFavouriteProductDetailsEntity
import com.flea.market.favourite.repository.FavouriteRepository
import com.flea.market.favourite.ui.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

private const val DELAY_FOR_SIMULATING_LOADING = 1000L

internal class FavouriteListViewModel(
    private val favouriteRepository: FavouriteRepository,
    private val cartRepository: CartRepository
) : ViewModelContract<FavouriteListUiState, FavouriteListIntent>, ViewModel() {

    private val _uiState: MutableStateFlow<FavouriteListUiState> = MutableStateFlow(Loading)
    override val uiState: StateFlow<FavouriteListUiState> = _uiState.asStateFlow()
    private var recentlyMovedItem: FavouriteItemViewEntity? = null

    init {
        viewModelScope.launch {
            favouriteRepository.getFavouriteProductsStream().map { it.toFavouriteItemViewEntity() }
                .map { favouriteItemList ->
                    if (favouriteItemList.isNotEmpty()) {
                        Content(
                            favouriteProductList = favouriteItemList,
                            snackbarDetails = with(uiState.value) {
                                if (this is Content) snackbarDetails else null
                            }
                        )
                    } else {
                        Empty
                    }
                }.catch { emit(Error(it)) }.onStart { delay(DELAY_FOR_SIMULATING_LOADING) }
                .collect { _uiState.value = it }
        }
    }

    override fun processIntent(intent: FavouriteListIntent) {
        when (intent) {
            is MoveToCart -> viewModelScope.launch { moveToCart(intent.favouriteItemViewEntity) }
            is RemoveFromFavourite -> viewModelScope.launch { removeFromFavourite(intent.productId) }
            is SnackbarResult -> snackbarResult(intent)
        }
    }

    private fun snackbarResult(intent: SnackbarResult) {
        uiState.ifInstanceOf<Content> { _uiState.value = it.copy(snackbarDetails = null) }
        if (intent.isActionPerformed) {
            recentlyMovedItem?.let {
                viewModelScope.launch {
                    favouriteRepository.markAsFavourite(it.toFavouriteProductDetailsEntity())
                    cartRepository.removeItem(it.id)
                }
            }
        }
        recentlyMovedItem = null
    }

    private suspend fun removeFromFavourite(productId: Int) {
        favouriteRepository.removeFromFavourite(productId)
    }

    private suspend fun moveToCart(favouriteItemViewEntity: FavouriteItemViewEntity) {
        uiState.ifInstanceOf<Content> { content ->
            recentlyMovedItem = favouriteItemViewEntity
            cartRepository.addOrUpdateItem(favouriteItemViewEntity.toCartProductDetails())
                .onSuccess {
                    val snackbarDetails = SnackbarWithActionDetails(
                        message = R.string.move_to_cart_success,
                        actionLabel = R.string.undo,
                        snackbarType = SUCCESS
                    )
                    _uiState.value = content.copy(snackbarDetails = snackbarDetails)

                    favouriteRepository.removeFromFavourite(favouriteItemViewEntity.id)
                }
        }
    }
}
