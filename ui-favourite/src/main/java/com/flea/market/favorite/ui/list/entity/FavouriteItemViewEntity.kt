package com.flea.market.favorite.ui.list.entity

internal data class FavouriteItemViewEntity(
    val category: String,
    val description: String,
    val id: Int,
    val image: String,
    val price: Double,
    val rating: Double,
    val title: String
)