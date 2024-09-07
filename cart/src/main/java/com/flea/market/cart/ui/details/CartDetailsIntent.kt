package com.flea.market.cart.ui.details

import com.flea.market.cart.ui.details.entity.ItemsInCartViewEntity

internal sealed class CartDetailsIntent {
    data class IncreaseQuantity(val itemsInCartViewEntity: ItemsInCartViewEntity) :
        CartDetailsIntent()

    data class DecreaseQuantity(val itemsInCartViewEntity: ItemsInCartViewEntity) :
        CartDetailsIntent()

    data class RemoveFromCart(val itemsInCartViewEntity: ItemsInCartViewEntity) :
        CartDetailsIntent()
}
