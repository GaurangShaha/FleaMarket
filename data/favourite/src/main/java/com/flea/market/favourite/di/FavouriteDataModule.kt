package com.flea.market.favourite.di

import com.flea.market.favourite.repository.FavouriteRepository
import com.flea.market.favourite.repository.FavouriteRepositoryImpl
import org.koin.dsl.module

val favouriteDataModule = module {
    single<FavouriteRepository> {
        FavouriteRepositoryImpl(
            favouriteLocalSource = get()
        )
    }
}