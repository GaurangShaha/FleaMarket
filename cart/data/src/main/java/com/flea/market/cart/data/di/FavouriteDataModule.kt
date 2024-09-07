package com.flea.market.cart.data.di

import com.flea.market.cart.data.repository.CartRepository
import com.flea.market.cart.data.repository.CartRepositoryImpl
import org.koin.core.module.Module
import org.koin.dsl.module

public val cartDataModule: Module = module {
    single<CartRepository> {
        CartRepositoryImpl(cartLocalSource = get())
    }
}
