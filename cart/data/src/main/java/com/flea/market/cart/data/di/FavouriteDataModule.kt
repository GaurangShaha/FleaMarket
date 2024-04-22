package com.flea.market.cart.data.di

import com.flea.market.cart.data.repository.CartRepository
import com.flea.market.cart.data.repository.CartRepositoryImpl
import org.koin.dsl.module

val cartDataModule = module {
    single<CartRepository> {
        CartRepositoryImpl(cartLocalSource = get())
    }
}
