package com.flea.market.profile.ui.di

import com.flea.market.profile.ui.ProfileViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.Module
import org.koin.dsl.module

public val profilePresentationModule: Module = module {
    viewModelOf(::ProfileViewModel)
}
