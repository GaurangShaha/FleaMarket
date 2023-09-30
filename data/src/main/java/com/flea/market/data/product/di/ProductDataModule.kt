package com.flea.market.data.product.di

import com.flea.market.data.product.remote.source.ProductRemoteSource
import com.flea.market.data.product.repository.ProductRepository
import com.flea.market.data.product.repository.ProductRepositoryImpl
import org.koin.dsl.module
import retrofit2.Retrofit

val productDataModule = module {
    single<ProductRepository> { ProductRepositoryImpl(productRemoteSource = get()) }

    single<ProductRemoteSource> { get<Retrofit>().create(ProductRemoteSource::class.java) }
}