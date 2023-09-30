package com.flea.cart.ui.details

import com.flea.cart.ui.details.entity.ItemsInCartViewEntity

internal sealed class CartDetailsIntent {
    object Checkout : CartDetailsIntent()
    data class IncreaseQuantity(val itemsInCartViewEntity: ItemsInCartViewEntity) :
        CartDetailsIntent()

    data class DecreaseQuantity(val itemsInCartViewEntity: ItemsInCartViewEntity) :
        CartDetailsIntent()

    data class RemoveFromCart(val itemsInCartViewEntity: ItemsInCartViewEntity) :
        CartDetailsIntent()
}