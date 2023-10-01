package com.flea.market.favorite.ui.di

import com.flea.market.common.slice.SnackBarSliceImpl
import com.flea.market.favorite.ui.list.FavouriteListViewModel
import com.flea.market.ui.slice.SnackBarSlice
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val favouritePresentationModule = module {
    viewModelOf(::FavouriteListViewModel)

    factory<SnackBarSlice> { SnackBarSliceImpl() }
}