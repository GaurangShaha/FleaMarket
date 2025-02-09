package com.flea.market.product.data.remote.entity

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
public data class ProductDetailsEntity(
    val category: String,
    val description: String,
    val id: Int,
    val image: String,
    val price: Double,
    val rating: RatingEntity,
    val title: String,
    val imageList: List<String> = emptyList()
)
