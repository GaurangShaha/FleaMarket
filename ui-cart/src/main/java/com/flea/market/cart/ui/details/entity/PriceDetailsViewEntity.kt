package com.flea.market.cart.ui.details.entity

internal data class PriceDetailsViewEntity(
    val subTotal: Double,
    val discount: Double,
    val tax: Double,
    val totalPayable: Double
)