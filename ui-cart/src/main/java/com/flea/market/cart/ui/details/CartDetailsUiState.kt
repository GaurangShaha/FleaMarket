package com.flea.market.cart.ui.details

import com.flea.market.cart.ui.details.entity.ItemsInCartViewEntity
import com.flea.market.cart.ui.details.entity.PriceDetailsViewEntity

internal sealed class CartDetailsUiState {
    object Loading : CartDetailsUiState()
    data class Content(
        val productList: List<ItemsInCartViewEntity>, val priceDetails: PriceDetailsViewEntity
    ) : CartDetailsUiState()

    object Empty : CartDetailsUiState()
    class Error(val throwable: Throwable) : CartDetailsUiState()
}
