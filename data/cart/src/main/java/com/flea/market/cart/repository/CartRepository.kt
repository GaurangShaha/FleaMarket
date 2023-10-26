package com.flea.market.cart.repository

import com.flea.market.cart.local.entity.CartProductDetailsEntity
import com.flea.market.foundation.model.Result
import kotlinx.coroutines.flow.Flow

interface CartRepository {
    suspend fun addOrUpdateItem(productDetails: CartProductDetailsEntity): Result<Unit, Throwable>

    suspend fun removeItem(productId: Int): Result<Unit, Throwable>

    suspend fun updateQuantity(productId: Int, quantity: Int): Result<Unit, Throwable>

    fun getItemsInCartStream(): Flow<List<CartProductDetailsEntity>>
}
