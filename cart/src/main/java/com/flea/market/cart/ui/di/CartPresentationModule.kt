package com.flea.market.cart.ui.di

import com.flea.market.cart.ui.details.CartDetailsViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

public val cartPresentationModule: Module = module {
    viewModelOf(::CartDetailsViewModel)
}
