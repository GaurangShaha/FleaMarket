package com.flea.market.cart.ui.details.entity

internal data class ItemsInCartViewEntity(
    val id: Int,
    val category: String,
    val image: String,
    val price: Double,
    val title: String,
    val quantity: Int
)