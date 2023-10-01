package com.flea.market.favourite.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FavouriteProductDetailsEntity(
    @ColumnInfo("id") @PrimaryKey val id: Int,
    @ColumnInfo("category") val category: String,
    @ColumnInfo("image") val image: String,
    @ColumnInfo("price") val price: Double,
    @ColumnInfo("title") val title: String,
    @ColumnInfo("description") val description: String,
    @ColumnInfo("rating") val rating: Double,
)