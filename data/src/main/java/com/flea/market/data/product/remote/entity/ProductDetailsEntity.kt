package com.flea.market.data.product.remote.entity

import com.squareup.moshi.Json

data class ProductDetailsEntity(
    @Json(name = "category") val category: String,
    @Json(name = "description") val description: String,
    @Json(name = "id") val id: Int,
    @Json(name = "image") val image: String,
    @Json(name = "price") val price: Double,
    @Json(name = "rating") val rating: RatingEntity,
    @Json(name = "title") val title: String,
    val imageList: List<String>
)

