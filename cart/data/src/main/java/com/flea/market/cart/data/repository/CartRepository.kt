package com.flea.market.cart.data.repository

import com.flea.market.cart.data.local.entity.CartProductDetailsEntity
import com.flea.market.foundation.model.Result
import kotlinx.coroutines.flow.Flow

public interface CartRepository {
    public suspend fun addOrUpdateItem(productDetails: CartProductDetailsEntity): Result<Unit>

    public suspend fun removeItem(productId: Int): Result<Unit>

    public suspend fun updateQuantity(productId: Int, quantity: Int): Result<Unit>

    public fun getItemsInCartStream(): Flow<List<CartProductDetailsEntity>>
}
