package com.flea.market.cart.data.repository

import com.flea.market.cart.data.local.entity.CartProductDetailsEntity
import com.flea.market.cart.data.local.source.CartLocalSource
import com.flea.market.foundation.helper.executeSafely

internal class DefaultCartRepository(private val cartLocalSource: CartLocalSource) : CartRepository {
    override suspend fun addOrUpdateItem(productDetails: CartProductDetailsEntity) =
        executeSafely {
            cartLocalSource.addOrUpdateProduct(
                getExistingProductWithIncrementedQuantity(productDetails.id) ?: productDetails
            )
        }

    override suspend fun removeItem(productId: Int) = executeSafely {
        cartLocalSource.removeProduct(productId)
    }

    override suspend fun updateQuantity(productId: Int, quantity: Int) = executeSafely {
        cartLocalSource.addOrUpdateProduct(getExistingProduct(productId)!!.copy(quantity = quantity))
    }

    override fun getItemsInCartStream() = cartLocalSource.getItemsInCartStream()

    private suspend fun getExistingProduct(productId: Int) =
        cartLocalSource.getProductById(productId)

    private suspend fun getExistingProductWithIncrementedQuantity(productId: Int): CartProductDetailsEntity? =
        getExistingProduct(productId)?.let {
            it.copy(quantity = it.quantity + 1)
        }
}
