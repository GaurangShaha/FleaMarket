package com.flea.market.favorite.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.flea.market.cart.data.repository.CartRepository
import com.flea.market.common.contract.viewmodel.ViewModelContract
import com.flea.market.common.extension.ifInstanceOf
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
import com.flea.market.favourite.ui.R
import com.flea.market.foundation.extension.onSuccess
import com.flea.market.ui.component.SnackbarDelegate.SnackbarType.SUCCESS
import com.flea.market.ui.component.SnackbarDetails
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

    override fun onHandleIntent(intent: FavouriteListIntent) {
        when (intent) {
            is MoveToCart -> viewModelScope.launch { moveToCart(intent.favouriteItemViewEntity) }
            is RemoveFromFavourite -> viewModelScope.launch { removeFromFavourite(intent.productId) }
            is SnackbarResult -> snackbarResult()
        }
    }

    private fun snackbarResult() {
        uiState.ifInstanceOf<Content> { _uiState.value = it.copy(snackbarDetails = null) }
    }

    private suspend fun removeFromFavourite(productId: Int) {
        favouriteRepository.removeFromFavourite(productId)
    }

    private suspend fun moveToCart(favouriteItemViewEntity: FavouriteItemViewEntity) {
        uiState.ifInstanceOf<Content> { content ->
            cartRepository.addOrUpdateItem(favouriteItemViewEntity.toCartProductDetails())
                .onSuccess {
                    val snackbarDetails = SnackbarDetails(
                        message = R.string.move_to_cart_success,
                        snackbarType = SUCCESS
                    )
                    _uiState.value = content.copy(snackbarDetails = snackbarDetails)

                    favouriteRepository.removeFromFavourite(favouriteItemViewEntity.id)
                }
        }
    }
}
