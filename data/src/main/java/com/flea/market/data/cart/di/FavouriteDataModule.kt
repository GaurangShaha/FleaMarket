package com.flea.market.data.cart.di

import com.flea.market.data.cart.repository.CartRepository
import com.flea.market.data.cart.repository.CartRepositoryImpl
import com.flea.market.data.common.local.FleaMarketDatabase
import org.koin.dsl.module

val cartDataModule = module {
    single { get<FleaMarketDatabase>().cartLocalSource() }

    single<CartRepository> {
        CartRepositoryImpl(cartLocalSource = get())
    }
}