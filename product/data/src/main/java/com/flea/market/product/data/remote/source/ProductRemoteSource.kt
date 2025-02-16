package com.flea.market.product.data.remote.source

import android.artisan.foundation.model.Result
import com.flea.market.product.data.remote.entity.ProductDetailsEntity
import retrofit2.http.GET
import retrofit2.http.Path

internal interface ProductRemoteSource {
    @GET("/products")
    suspend fun getProductList(): Result<List<ProductDetailsEntity>>

    @GET("/products/{id}")
    suspend fun getProductDetails(@Path("id") id: Int): Result<ProductDetailsEntity>
}
