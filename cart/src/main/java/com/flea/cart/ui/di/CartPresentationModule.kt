package com.flea.cart.ui.di

import com.flea.cart.ui.details.CartDetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val cartPresentationModule = module {
    viewModelOf(::CartDetailsViewModel)
}