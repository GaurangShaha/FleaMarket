package com.flea.market.profile.ui.di

import com.flea.market.profile.ui.ProfileViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

public val profilePresentationModule: Module = module {
    viewModelOf(::ProfileViewModel)
}
