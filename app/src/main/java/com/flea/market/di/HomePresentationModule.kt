package com.flea.market.di

import com.flea.market.ui.main.MainViewModel
import com.flea.market.ui.main.NavigationBarVMSlice
import com.flea.market.ui.main.NavigationBarVMSliceImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

internal val homePresentationModule = module {
    viewModel { MainViewModel(navigationBarVMSlice = get()) }
    factory<NavigationBarVMSlice> { NavigationBarVMSliceImpl() }
}
