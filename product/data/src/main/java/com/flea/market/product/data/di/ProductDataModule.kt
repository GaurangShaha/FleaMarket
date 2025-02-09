package com.flea.market.product.data.di

import com.flea.market.product.data.remote.source.ProductRemoteSource
import com.flea.market.product.data.repository.DefaultProductRepository
import com.flea.market.product.data.repository.ProductRepository
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit

public val productDataModule: Module = module {
    single<ProductRepository> { DefaultProductRepository(productRemoteSource = get()) }

    single<ProductRemoteSource> { get<Retrofit>().create(ProductRemoteSource::class.java) }
}
