package com.flea.market.favourite.repository

import android.artisan.foundation.helper.executeSafely
import com.flea.market.favourite.local.entity.FavouriteProductDetailsEntity
import com.flea.market.favourite.local.source.FavouriteLocalSource

@Suppress("TooGenericExceptionCaught")
internal class DefaultFavouriteRepository(
    private val favouriteLocalSource: FavouriteLocalSource
) : FavouriteRepository {

    override suspend fun markAsFavourite(favouriteProductDetailsEntity: FavouriteProductDetailsEntity) =
        executeSafely {
            favouriteLocalSource.addFavouriteProduct(favouriteProductDetailsEntity)
        }

    override suspend fun removeFromFavourite(productId: Int) = executeSafely {
        favouriteLocalSource.removeFavouriteProduct(productId)
    }

    override suspend fun isMarkedAsFavourite(productId: Int) = executeSafely {
        favouriteLocalSource.getFavouriteProductById(productId) != null
    }

    override fun getFavouriteProductsStream() = favouriteLocalSource.getFavouriteProductsStream()
}
