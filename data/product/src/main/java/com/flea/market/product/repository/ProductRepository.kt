package com.flea.market.product.repository

import com.flea.market.foundation.model.Result
import com.flea.market.product.remote.entity.ProductDetailsEntity

interface ProductRepository {
    suspend fun getProductList(): Result<List<ProductDetailsEntity>, Throwable>

    suspend fun getProductDetails(id: Int): Result<ProductDetailsEntity, Throwable>
}
