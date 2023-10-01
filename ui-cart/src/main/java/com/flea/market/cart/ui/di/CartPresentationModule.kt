package com.flea.market.cart.ui.di

import com.flea.market.cart.ui.details.CartDetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val cartPresentationModule = module {
    viewModelOf(::CartDetailsViewModel)
}