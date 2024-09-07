package com.flea.market.product.data.repository

import com.flea.market.foundation.model.Result
import com.flea.market.product.data.remote.entity.ProductDetailsEntity

public interface ProductRepository {
    public suspend fun getProductList(): Result<List<ProductDetailsEntity>, Throwable>

    public suspend fun getProductDetails(id: Int): Result<ProductDetailsEntity, Throwable>
}
