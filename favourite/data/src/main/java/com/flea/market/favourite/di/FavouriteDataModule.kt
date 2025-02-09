package com.flea.market.favourite.di

import com.flea.market.favourite.repository.DefaultFavouriteRepository
import com.flea.market.favourite.repository.FavouriteRepository
import org.koin.core.module.Module
import org.koin.dsl.module

public val favouriteDataModule: Module = module {
    single<FavouriteRepository> {
        DefaultFavouriteRepository(
            favouriteLocalSource = get()
        )
    }
}
