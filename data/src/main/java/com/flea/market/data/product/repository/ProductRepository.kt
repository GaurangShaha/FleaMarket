package com.flea.market.data.product.repository

import com.flea.market.data.product.remote.entity.ProductDetailsEntity
import com.flea.market.foundation.model.Result

interface ProductRepository {
    suspend fun getProductList(): Result<List<ProductDetailsEntity>, Throwable>

    suspend fun getProductDetails(id: Int): Result<ProductDetailsEntity, Throwable>
}