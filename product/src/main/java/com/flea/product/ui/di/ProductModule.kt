package com.flea.product.ui.di

import androidx.lifecycle.SavedStateHandle
import com.flea.product.ui.details.ProductDetailsViewModel
import com.flea.product.ui.details.navigation.ProductDetailsArgs
import com.flea.product.ui.list.ProductListViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val productPresentationModule = module {
    viewModelOf(::ProductListViewModel)

    factory { ProductDetailsArgs(get<SavedStateHandle>()) }
    viewModelOf(::ProductDetailsViewModel)
}