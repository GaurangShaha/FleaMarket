package com.flea.market.favourite.repository

import com.flea.market.favourite.local.entity.FavouriteProductDetailsEntity
import com.flea.market.favourite.local.source.FavouriteLocalSource
import com.flea.market.foundation.helper.executeCatching

@Suppress("TooGenericExceptionCaught")
internal class FavouriteRepositoryImpl(
    private val favouriteLocalSource: FavouriteLocalSource
) : FavouriteRepository {

    override suspend fun markAsFavourite(favouriteProductDetailsEntity: FavouriteProductDetailsEntity) =
        executeCatching {
            favouriteLocalSource.addFavouriteProduct(favouriteProductDetailsEntity)
        }

    override suspend fun removeFromFavourite(productId: Int) = executeCatching {
        favouriteLocalSource.removeFavouriteProduct(productId)
    }

    override suspend fun isMarkedAsFavourite(productId: Int) = executeCatching {
        favouriteLocalSource.getFavouriteProductById(productId) != null
    }

    override fun getFavouriteProductsStream() = favouriteLocalSource.getFavouriteProductsStream()
}
