package com.flea.market.product.data.remote.entity

import com.squareup.moshi.Json

public data class RatingEntity(@Json(name = "count") val count: Int, @Json(name = "rate") val rate: Double)
