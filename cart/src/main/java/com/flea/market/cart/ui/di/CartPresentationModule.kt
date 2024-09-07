package com.flea.market.cart.ui.di

import com.flea.market.cart.ui.details.CartDetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.Module
import org.koin.dsl.module

public val cartPresentationModule: Module = module {
    viewModelOf(::CartDetailsViewModel)
}
