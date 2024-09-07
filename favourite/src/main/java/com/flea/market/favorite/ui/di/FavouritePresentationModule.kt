package com.flea.market.favorite.ui.di

import com.flea.market.favorite.ui.list.FavouriteListViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val favouritePresentationModule = module {
    viewModelOf(::FavouriteListViewModel)
}
