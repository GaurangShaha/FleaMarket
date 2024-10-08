package com.flea.market.product.ui.di

import androidx.lifecycle.SavedStateHandle
import com.flea.market.product.ui.details.ProductDetailsViewModel
import com.flea.market.product.ui.details.navigation.ProductDetailsArgs
import com.flea.market.product.ui.list.ProductListViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.Module
import org.koin.dsl.module

public val productPresentationModule: Module = module {
    viewModelOf(::ProductListViewModel)

    factory { ProductDetailsArgs(get<SavedStateHandle>()) }
    viewModelOf(::ProductDetailsViewModel)
}
