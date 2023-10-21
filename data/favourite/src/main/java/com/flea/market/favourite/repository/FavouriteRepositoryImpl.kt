package com.flea.market.favourite.repository

import com.flea.market.favourite.local.entity.FavouriteProductDetailsEntity
import com.flea.market.favourite.local.source.FavouriteLocalSource
import com.flea.market.foundation.model.Result
import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext

@Suppress("TooGenericExceptionCaught")
internal class FavouriteRepositoryImpl(
    private val executionContext: CoroutineContext = Dispatchers.IO,
    private val favouriteLocalSource: FavouriteLocalSource
) : FavouriteRepository {


    override suspend fun markAsFavourite(favouriteProductDetailsEntity: FavouriteProductDetailsEntity) =
        withContext(executionContext) {
            try {
                favouriteLocalSource.addFavouriteProduct(favouriteProductDetailsEntity)
                Result.success(Unit)
            } catch (e: Exception) {
                Result.failure(e)
            }
        }

    override suspend fun removeFromFavourite(productId: Int) = withContext(executionContext) {
        try {
            favouriteLocalSource.removeFavouriteProduct(productId)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun isMarkedAsFavourite(productId: Int) = withContext(executionContext) {
        try {
            Result.success(favouriteLocalSource.getFavouriteProductById(productId) != null)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override fun getFavouriteProducts() =
        favouriteLocalSource.getFavouriteProducts().flowOn(executionContext)
}