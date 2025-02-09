package com.flea.market.di

import com.flea.market.ui.main.MainViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

internal val homePresentationModule = module {
    viewModelOf(::MainViewModel)
}
