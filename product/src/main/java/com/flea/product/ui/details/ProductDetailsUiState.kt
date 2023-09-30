package com.flea.product.ui.details

import com.flea.common.ui.component.ButtonState
import com.flea.product.ui.common.entity.ProductDetailsViewEntity
import kotlinx.coroutines.flow.StateFlow

internal sealed class ProductDetailsUiState {
    object Loading : ProductDetailsUiState()
    class Error(val throwable: Throwable) : ProductDetailsUiState()
    data class Content(
        val productDetails: ProductDetailsViewEntity,
        val markedAsFavouriteFlow: StateFlow<Boolean>,
        val addToCartButtonState: StateFlow<ButtonState>
    ) : ProductDetailsUiState()
}