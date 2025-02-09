package com.flea.market.product.ui.di

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.toRoute
import com.flea.market.common.navigation.ProductDetailsDestination
import com.flea.market.product.ui.details.ProductDetailsViewModel
import com.flea.market.product.ui.list.ProductListViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

public val productPresentationModule: Module = module {
    viewModelOf(::ProductListViewModel)

    factory { get<SavedStateHandle>().toRoute<ProductDetailsDestination>() }
    viewModelOf(::ProductDetailsViewModel)
}
