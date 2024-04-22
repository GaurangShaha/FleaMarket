package com.flea.market.favourite.repository

import com.flea.market.favourite.local.entity.FavouriteProductDetailsEntity
import com.flea.market.favourite.local.source.FavouriteLocalSource
import com.flea.market.foundation.model.Result

@Suppress("TooGenericExceptionCaught")
internal class FavouriteRepositoryImpl(
    private val favouriteLocalSource: FavouriteLocalSource
) : FavouriteRepository {

    override suspend fun markAsFavourite(favouriteProductDetailsEntity: FavouriteProductDetailsEntity) =
        try {
            favouriteLocalSource.addFavouriteProduct(favouriteProductDetailsEntity)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }

    override suspend fun removeFromFavourite(productId: Int) = try {
        favouriteLocalSource.removeFavouriteProduct(productId)
        Result.success(Unit)
    } catch (e: Exception) {
        Result.failure(e)
    }

    override suspend fun isMarkedAsFavourite(productId: Int) = try {
        Result.success(favouriteLocalSource.getFavouriteProductById(productId) != null)
    } catch (e: Exception) {
        Result.failure(e)
    }

    override fun getFavouriteProductsStream() = favouriteLocalSource.getFavouriteProductsStream()
}
