package com.flea.market.product.ui.details

import androidx.lifecycle.viewModelScope
import com.flea.market.common.base.viewmodel.BaseViewModel
import com.flea.market.data.cart.repository.CartRepository
import com.flea.market.data.favourite.repository.FavouriteRepository
import com.flea.market.data.product.remote.entity.ProductDetailsEntity
import com.flea.market.data.product.repository.ProductRepository
import com.flea.market.foundation.extension.fold
import com.flea.market.foundation.extension.onSuccess
import com.flea.market.product.ui.common.mapper.toProductDetailsViewEntity
import com.flea.market.product.ui.details.ProductDetailsIntent.AddToCart
import com.flea.market.product.ui.details.ProductDetailsIntent.ToggleMarkAsFavourite
import com.flea.market.product.ui.details.ProductDetailsUiState.Content
import com.flea.market.product.ui.details.ProductDetailsUiState.Error
import com.flea.market.product.ui.details.ProductDetailsUiState.Loading
import com.flea.market.product.ui.details.mapper.toCartProductDetails
import com.flea.market.product.ui.details.mapper.toFavouriteProductDetails
import com.flea.market.product.ui.details.navigation.ProductDetailsArgs
import com.flea.market.ui.component.ButtonState
import com.flea.market.ui.component.ButtonState.Initial
import com.flea.market.ui.component.ButtonState.Result
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.random.Random

internal class ProductDetailsViewModel(
    private val productDetailsArgs: ProductDetailsArgs,
    private val productRepository: ProductRepository,
    private val cartRepository: CartRepository,
    private val favouriteRepository: FavouriteRepository
) : BaseViewModel<ProductDetailsIntent, ProductDetailsUiState>(Loading) {
    private val markedAsFavouriteStateFlow = MutableStateFlow(false)
    private val addToCartButtonState: MutableStateFlow<ButtonState> = MutableStateFlow(Initial)

    init {
        getProductDetails()
    }

    override fun handleIntent(intent: ProductDetailsIntent) {
        when (intent) {
            AddToCart -> addToCart()
            is ToggleMarkAsFavourite -> toggleMarkAsFavourite(intent.markAsFavourite)
            ProductDetailsIntent.Reload -> getProductDetails()
        }
    }

    private fun getProductDetails() {
        viewModelScope.launch {
            updateUiState(Loading)
            favouriteRepository.isMarkedAsFavourite(productDetailsArgs.productId)
                .onSuccess { markedAsFavouriteStateFlow.value = it }
            productRepository.getProductDetails(productDetailsArgs.productId)
                .fold(::handleGetProductDetailsSuccess, ::handleGetProductDetailsFailure)
        }
    }

    private fun addToCart() {
        val currentUiState = uiState.value
        if (currentUiState is Content) {
            viewModelScope.launch {
                addToCartButtonState.value = ButtonState.Loading
                delay(Random.nextLong(100, 3000))
                cartRepository.addOrUpdateItem(currentUiState.productDetails.toCartProductDetails())
                    .onSuccess {
                        addToCartButtonState.value = Result
                        //Code to reset the button after
                        delay(4000)
                        addToCartButtonState.value = Initial
                    }
            }
        }
    }

    private fun toggleMarkAsFavourite(markAsFavourite: Boolean) {
        val currentUiState = uiState.value

        if (currentUiState is Content) {
            viewModelScope.launch {
                if (markAsFavourite) {
                    favouriteRepository.markAsFavourite(currentUiState.productDetails.toFavouriteProductDetails())
                        .onSuccess { markedAsFavouriteStateFlow.value = true }
                } else {
                    favouriteRepository.removeFromFavourite(currentUiState.productDetails.id)
                        .onSuccess { markedAsFavouriteStateFlow.value = false }
                }
            }
        }
    }

    private fun handleGetProductDetailsFailure(throwable: Throwable) {
        updateUiState(Error(throwable))
    }

    private fun handleGetProductDetailsSuccess(productDetailsEntity: ProductDetailsEntity) {
        updateUiState(
            Content(
                productDetails = productDetailsEntity.toProductDetailsViewEntity(),
                markedAsFavouriteFlow = markedAsFavouriteStateFlow.asStateFlow(),
                addToCartButtonState = addToCartButtonState.asStateFlow()
            )
        )
    }
}
