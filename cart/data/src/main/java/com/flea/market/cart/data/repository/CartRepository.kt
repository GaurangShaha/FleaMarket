package com.flea.market.cart.data.repository

import com.flea.market.cart.data.local.entity.CartProductDetailsEntity
import com.flea.market.foundation.model.Result
import kotlinx.coroutines.flow.Flow

public interface CartRepository {
    public suspend fun addOrUpdateItem(productDetails: CartProductDetailsEntity): Result<Unit, Throwable>

    public suspend fun removeItem(productId: Int): Result<Unit, Throwable>

    public suspend fun updateQuantity(productId: Int, quantity: Int): Result<Unit, Throwable>

    public fun getItemsInCartStream(): Flow<List<CartProductDetailsEntity>>
}
