package com.flea.market.cart.data.local.source

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.flea.market.cart.data.local.entity.CartProductDetailsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CartLocalSource {
    @Query("SELECT * FROM cartProductDetailsEntity")
    fun getItemsInCartStream(): Flow<List<CartProductDetailsEntity>>

    @Upsert
    suspend fun addOrUpdateProduct(productDetails: CartProductDetailsEntity)

    @Query("SELECT * FROM cartProductDetailsEntity WHERE id= :productId")
    suspend fun getProductById(productId: Int): CartProductDetailsEntity?

    @Query("DELETE FROM cartProductDetailsEntity WHERE id = :productId")
    suspend fun removeProduct(productId: Int)
}
