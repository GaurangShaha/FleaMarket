package com.flea.market.product.ui.common.entity

internal data class ProductDetailsViewEntity(
    val category: String,
    val description: String,
    val id: Int,
    val imageList: List<String>,
    val price: Double,
    val count: Int,
    val rate: Double,
    val title: String
)