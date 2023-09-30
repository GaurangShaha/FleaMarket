package com.flea.market.data.product.remote.source

import com.flea.market.data.product.remote.entity.ProductDetailsEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

internal interface ProductRemoteSource {
    @GET("/products")
    suspend fun getProductList(): Response<List<ProductDetailsEntity>>

    @GET("/products/{id}")
    suspend fun getProductDetails(@Path("id") id: Int): Response<ProductDetailsEntity>
}