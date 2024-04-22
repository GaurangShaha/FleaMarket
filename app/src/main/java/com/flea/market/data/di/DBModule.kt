package com.flea.market.data.di

import com.flea.market.data.local.DatabaseProvider
import com.flea.market.data.local.FleaMarketDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

internal val dbModule = module {
    single { DatabaseProvider.getDatabase(androidApplication()) }

    single { get<FleaMarketDatabase>().favouriteLocalSource() }

    single { get<FleaMarketDatabase>().cartLocalSource() }
}
