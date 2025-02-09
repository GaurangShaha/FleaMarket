package com.flea.market.product.data.remote.entity

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
public data class RatingEntity(val count: Int, val rate: Double)
