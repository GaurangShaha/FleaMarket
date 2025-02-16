package com.flea.market.product.ui.details

import android.artisan.ui.component.ButtonState
import com.flea.market.product.ui.common.entity.ProductDetailsViewEntity

internal sealed class ProductDetailsUiState {
    object Loading : ProductDetailsUiState()
    class Error(val throwable: Throwable) : ProductDetailsUiState()
    data class Content(
        val productDetails: ProductDetailsViewEntity,
        val markedAsFavourite: Boolean,
        val addToCartButtonState: ButtonState
    ) : ProductDetailsUiState()
}
