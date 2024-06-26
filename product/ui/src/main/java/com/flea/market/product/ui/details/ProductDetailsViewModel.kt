package com.flea.market.product.ui.details

import androidx.lifecycle.viewModelScope
import com.flea.market.cart.data.repository.CartRepository
import com.flea.market.common.base.viewmodel.BaseViewModel
import com.flea.market.favourite.repository.FavouriteRepository
import com.flea.market.foundation.extension.fold
import com.flea.market.foundation.extension.getOrElse
import com.flea.market.foundation.extension.onSuccess
import com.flea.market.product.data.remote.entity.ProductDetailsEntity
import com.flea.market.product.data.repository.ProductRepository
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
import kotlinx.coroutines.launch
import kotlin.random.Random

internal class ProductDetailsViewModel(
    private val productDetailsArgs: ProductDetailsArgs,
    private val productRepository: ProductRepository,
    private val cartRepository: CartRepository,
    private val favouriteRepository: FavouriteRepository
) : BaseViewModel<ProductDetailsIntent, ProductDetailsUiState>(Loading) {

    init {
        getProductDetails()
    }

    override fun onHandleIntent(intent: ProductDetailsIntent) {
        when (intent) {
            AddToCart -> addToCart()
            is ToggleMarkAsFavourite -> toggleMarkAsFavourite(intent.markAsFavourite)
            ProductDetailsIntent.Reload -> getProductDetails()
        }
    }

    private fun getProductDetails() {
        viewModelScope.launch {
            updateUiState(Loading)
            productRepository.getProductDetails(productDetailsArgs.productId)
                .fold(::handleGetProductDetailsSuccess, ::handleGetProductDetailsFailure)
        }
    }

    private fun addToCart() {
        uiState.ifInstanceOf<Content> { content ->
            viewModelScope.launch {
                updateUiState(content.copy(addToCartButtonState = ButtonState.Loading))
                delay(Random.nextLong(MIN_DELAY, MAX_DELAY))
                cartRepository.addOrUpdateItem(content.productDetails.toCartProductDetails())
                    .onSuccess {
                        updateUiState(content.copy(addToCartButtonState = Result))
                        // Code to reset the button after
                        delay(RESET_DELAY)
                        updateUiState(content.copy(addToCartButtonState = Initial))
                    }
            }
        }
    }

    private fun toggleMarkAsFavourite(markAsFavourite: Boolean) {
        uiState.ifInstanceOf<Content> { content ->
            viewModelScope.launch {
                if (markAsFavourite) {
                    favouriteRepository.markAsFavourite(content.productDetails.toFavouriteProductDetails())
                        .onSuccess { updateUiState(content.copy(markedAsFavourite = true)) }
                } else {
                    favouriteRepository.removeFromFavourite(content.productDetails.id)
                        .onSuccess { updateUiState(content.copy(markedAsFavourite = false)) }
                }
            }
        }
    }

    private fun handleGetProductDetailsFailure(throwable: Throwable) {
        updateUiState(Error(throwable))
    }

    private fun handleGetProductDetailsSuccess(productDetailsEntity: ProductDetailsEntity) {
        viewModelScope.launch {
            val markedAsFavourite = favouriteRepository.isMarkedAsFavourite(productDetailsEntity.id)
                .getOrElse(false)

            updateUiState(
                Content(
                    productDetails = productDetailsEntity.toProductDetailsViewEntity(),
                    markedAsFavourite = markedAsFavourite,
                    addToCartButtonState = Initial
                )
            )
        }
    }

    companion object {
        private const val MIN_DELAY: Long = 100
        private const val MAX_DELAY: Long = 3000
        private const val RESET_DELAY: Long = 5000
    }
}
