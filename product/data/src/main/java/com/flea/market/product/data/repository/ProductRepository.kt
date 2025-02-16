package com.flea.market.product.data.repository

import android.artisan.foundation.model.Result
import com.flea.market.product.data.remote.entity.ProductDetailsEntity

public interface ProductRepository {
    public suspend fun getProductList(): Result<List<ProductDetailsEntity>>

    public suspend fun getProductDetails(id: Int): Result<ProductDetailsEntity>
}
