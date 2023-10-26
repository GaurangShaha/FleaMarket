package com.flea.market.cart.repository

import com.flea.market.cart.local.entity.CartProductDetailsEntity
import com.flea.market.cart.local.source.CartLocalSource
import com.flea.market.foundation.model.Result

@Suppress("TooGenericExceptionCaught")
internal class CartRepositoryImpl(private val cartLocalSource: CartLocalSource) : CartRepository {
    override suspend fun addOrUpdateItem(productDetails: CartProductDetailsEntity) =
        try {
            cartLocalSource.addOrUpdateProduct(
                getExistingProductWithIncrementedQuantity(productDetails.id) ?: productDetails
            )
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }

    override suspend fun removeItem(productId: Int) = try {
        cartLocalSource.removeProduct(productId)
        Result.success(Unit)
    } catch (e: Exception) {
        Result.failure(e)
    }

    override suspend fun updateQuantity(productId: Int, quantity: Int) =
        try {
            @Suppress("UnsafeCallOnNullableType")
            cartLocalSource.addOrUpdateProduct(getExistingProduct(productId)!!.copy(quantity = quantity))
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }

    override fun getItemsInCartStream() = cartLocalSource.getItemsInCartStream()

    private suspend fun getExistingProduct(productId: Int) =
        cartLocalSource.getProductById(productId)

    private suspend fun getExistingProductWithIncrementedQuantity(productId: Int): CartProductDetailsEntity? {
        return getExistingProduct(productId)?.let {
            it.copy(quantity = it.quantity + 1)
        }
    }
}
