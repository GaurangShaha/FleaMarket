package com.flea.market.product.ui.details

import com.flea.market.product.ui.common.entity.ProductDetailsViewEntity
import com.flea.market.ui.component.ButtonState

internal sealed class ProductDetailsUiState {
    object Loading : ProductDetailsUiState()
    class Error(val throwable: Throwable) : ProductDetailsUiState()
    data class Content(
        val productDetails: com.flea.market.product.ui.common.entity.ProductDetailsViewEntity,
        val markedAsFavourite: Boolean,
        val addToCartButtonState: ButtonState
    ) : ProductDetailsUiState()
}
