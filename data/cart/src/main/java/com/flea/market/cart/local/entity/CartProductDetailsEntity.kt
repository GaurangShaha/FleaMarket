package com.flea.market.cart.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CartProductDetailsEntity(
    @ColumnInfo("id") @PrimaryKey val id: Int,
    @ColumnInfo("category") val category: String,
    @ColumnInfo("image") val image: String,
    @ColumnInfo("price") val price: Double,
    @ColumnInfo("title") val title: String,
    @ColumnInfo("quantity") val quantity: Int
)
