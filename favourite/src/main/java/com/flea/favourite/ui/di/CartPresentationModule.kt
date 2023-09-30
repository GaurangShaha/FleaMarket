package com.flea.favourite.ui.di

import com.flea.common.ui.slice.SnackBarSlice
import com.flea.common.ui.slice.SnackBarSliceImpl
import com.flea.favourite.ui.list.FavouriteListViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val favouritePresentationModule = module {
    viewModelOf(::FavouriteListViewModel)

    factory<SnackBarSlice> { SnackBarSliceImpl() }
}