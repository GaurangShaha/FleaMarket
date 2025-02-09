package com.flea.market.product.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.flea.market.cart.data.repository.CartRepository
import com.flea.market.common.contract.viewmodel.ViewModelContract
import com.flea.market.common.extension.ifInstanceOf
import com.flea.market.common.navigation.ProductDetailsDestination
import com.flea.market.favourite.repository.FavouriteRepository
import com.flea.market.foundation.extension.getOrThrow
import com.flea.market.foundation.extension.onSuccess
import com.flea.market.product.data.repository.ProductRepository
import com.flea.market.product.ui.common.mapper.toProductDetailsViewEntity
import com.flea.market.product.ui.details.ProductDetailsIntent.AddToCart
import com.flea.market.product.ui.details.ProductDetailsIntent.Reload
import com.flea.market.product.ui.details.ProductDetailsIntent.ToggleMarkAsFavourite
import com.flea.market.product.ui.details.ProductDetailsUiState.Content
import com.flea.market.product.ui.details.ProductDetailsUiState.Error
import com.flea.market.product.ui.details.ProductDetailsUiState.Loading
import com.flea.market.product.ui.details.mapper.toCartProductDetails
import com.flea.market.product.ui.details.mapper.toFavouriteProductDetails
import com.flea.market.ui.component.ButtonState
import com.flea.market.ui.component.ButtonState.Initial
import com.flea.market.ui.component.ButtonState.Result
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.random.Random

internal class ProductDetailsViewModel(
    private val productDetailsDestination: ProductDetailsDestination,
    private val productRepository: ProductRepository,
    private val cartRepository: CartRepository,
    private val favouriteRepository: FavouriteRepository
) : ViewModelContract<ProductDetailsUiState, ProductDetailsIntent>, ViewModel() {

    private val _uiState: MutableStateFlow<ProductDetailsUiState> = MutableStateFlow(Loading)
    override val uiState: StateFlow<ProductDetailsUiState> = _uiState.asStateFlow()

    init {
        getProductDetails()
    }

    override fun onHandleIntent(intent: ProductDetailsIntent) {
        when (intent) {
            AddToCart -> viewModelScope.launch { addToCart() }
            is ToggleMarkAsFavourite -> viewModelScope.launch { toggleMarkAsFavourite(intent.markAsFavourite) }
            Reload -> getProductDetails()
        }
    }

    private fun getProductDetails() {
        val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
            _uiState.value = Error(throwable)
        }

        viewModelScope.launch(coroutineExceptionHandler) {
            _uiState.value = Loading
            val productDetailsDeferred = async {
                productRepository.getProductDetails(productDetailsDestination.productId).getOrThrow()
                    .toProductDetailsViewEntity()
            }
            val markedAsFavouriteDeferred = async {
                favouriteRepository.isMarkedAsFavourite(productDetailsDestination.productId).getOrThrow()
            }

            _uiState.value = Content(
                productDetails = productDetailsDeferred.await(),
                markedAsFavourite = markedAsFavouriteDeferred.await(),
                addToCartButtonState = Initial
            )
        }
    }

    private suspend fun addToCart() {
        uiState.ifInstanceOf<Content> { content ->
            _uiState.value = content.copy(addToCartButtonState = ButtonState.Loading)
            delay(Random.nextLong(MIN_DELAY, MAX_DELAY))
            cartRepository.addOrUpdateItem(content.productDetails.toCartProductDetails())
                .onSuccess {
                    _uiState.value = content.copy(addToCartButtonState = Result)
                    // Code to reset the button after
                    delay(RESET_DELAY)
                    _uiState.value = content.copy(addToCartButtonState = Initial)
                }
        }
    }

    private suspend fun toggleMarkAsFavourite(markAsFavourite: Boolean) {
        uiState.ifInstanceOf<Content> { content ->
            if (markAsFavourite) {
                favouriteRepository.markAsFavourite(content.productDetails.toFavouriteProductDetails())
                    .onSuccess { _uiState.value = content.copy(markedAsFavourite = true) }
            } else {
                favouriteRepository.removeFromFavourite(content.productDetails.id)
                    .onSuccess { _uiState.value = content.copy(markedAsFavourite = false) }
            }
        }
    }

    companion object {
        private const val MIN_DELAY: Long = 100
        private const val MAX_DELAY: Long = 3000
        private const val RESET_DELAY: Long = 5000
    }
}
