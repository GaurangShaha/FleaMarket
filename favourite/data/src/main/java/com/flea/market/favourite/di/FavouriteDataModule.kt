package com.flea.market.favourite.di

import com.flea.market.favourite.repository.FavouriteRepository
import com.flea.market.favourite.repository.FavouriteRepositoryImpl
import org.koin.core.module.Module
import org.koin.dsl.module

public val favouriteDataModule: Module = module {
    single<FavouriteRepository> {
        FavouriteRepositoryImpl(
            favouriteLocalSource = get()
        )
    }
}
