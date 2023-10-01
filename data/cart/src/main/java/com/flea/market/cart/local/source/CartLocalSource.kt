package com.flea.market.cart.local.source

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.flea.market.cart.local.entity.CartProductDetailsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CartLocalSource {
    @Query("SELECT * FROM cartProductDetailsEntity")
    fun getAll(): Flow<List<CartProductDetailsEntity>>

    @Upsert
    fun addOrUpdateProduct(productDetails: CartProductDetailsEntity)

    @Query("SELECT * FROM cartProductDetailsEntity WHERE id= :productId")
    fun getProductById(productId: Int): CartProductDetailsEntity?

    @Query("DELETE FROM cartProductDetailsEntity WHERE id = :productId")
    fun removeProduct(productId: Int)
}