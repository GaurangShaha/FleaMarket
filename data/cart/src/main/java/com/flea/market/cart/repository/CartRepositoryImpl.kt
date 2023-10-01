package com.flea.market.cart.repository

import com.flea.market.cart.local.entity.CartProductDetailsEntity
import com.flea.market.cart.local.source.CartLocalSource
import com.flea.market.foundation.model.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

internal class CartRepositoryImpl(
    private val executionContext: CoroutineContext = Dispatchers.IO,
    private val cartLocalSource: CartLocalSource
) : CartRepository {
    override suspend fun addOrUpdateItem(productDetails: CartProductDetailsEntity) =
        withContext(executionContext) {
            try {
                cartLocalSource.addOrUpdateProduct(
                    getExistingProductWithIncrementedQuantity(productDetails.id) ?: productDetails
                )
                Result.success(Unit)
            } catch (e: Exception) {
                Result.failure(e)
            }
        }

    override suspend fun removeItem(productId: Int) = withContext(executionContext) {
        try {
            cartLocalSource.removeProduct(productId)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun updateQuantity(productId: Int, quantity: Int) =
        withContext(executionContext) {
            try {
                cartLocalSource.addOrUpdateProduct(getExistingProduct(productId)!!.copy(quantity = quantity))
                Result.success(Unit)
            } catch (e: Exception) {
                Result.failure(e)
            }
        }

    override fun getAllItems() = cartLocalSource.getAll().flowOn(executionContext)

    private fun getExistingProduct(productId: Int) = cartLocalSource.getProductById(productId)

    private fun getExistingProductWithIncrementedQuantity(productId: Int): CartProductDetailsEntity? {
        return getExistingProduct(productId)?.let {
            it.copy(quantity = it.quantity + 1)
        }
    }
}