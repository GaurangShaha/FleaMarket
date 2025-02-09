package com.flea.market.favorite.ui.di

import com.flea.market.favorite.ui.list.FavouriteListViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

public val favouritePresentationModule: Module = module {
    viewModelOf(::FavouriteListViewModel)
}
