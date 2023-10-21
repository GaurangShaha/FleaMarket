package com.flea.market.product.remote.entity

import com.squareup.moshi.Json

data class RatingEntity(@Json(name = "count") val count: Int, @Json(name = "rate") val rate: Double)
