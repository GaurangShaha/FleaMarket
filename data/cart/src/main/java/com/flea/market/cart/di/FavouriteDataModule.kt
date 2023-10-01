package com.flea.market.cart.di

import com.flea.market.cart.repository.CartRepository
import com.flea.market.cart.repository.CartRepositoryImpl
import org.koin.dsl.module

val cartDataModule = module {
    single<CartRepository> {
        CartRepositoryImpl(cartLocalSource = get())
    }
}