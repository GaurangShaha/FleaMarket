package com.flea.market.data.favourite.di

import com.flea.market.data.common.local.FleaMarketDatabase
import com.flea.market.data.favourite.repository.FavouriteRepository
import com.flea.market.data.favourite.repository.FavouriteRepositoryImpl
import org.koin.dsl.module

val favouriteDataModule = module {
    single { get<FleaMarketDatabase>().favouriteLocalSource() }

    single<FavouriteRepository> {
        FavouriteRepositoryImpl(
            favouriteLocalSource = get()
        )
    }
}