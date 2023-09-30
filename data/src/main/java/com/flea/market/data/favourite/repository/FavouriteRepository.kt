package com.flea.market.data.favourite.repository

import com.flea.market.data.favourite.local.entity.FavouriteProductDetailsEntity
import com.flea.market.foundation.model.Result
import kotlinx.coroutines.flow.Flow

interface FavouriteRepository {
    suspend fun markAsFavourite(favouriteProductDetailsEntity: FavouriteProductDetailsEntity): Result<Unit, Throwable>

    suspend fun removeFromFavourite(productId: Int): Result<Unit, Throwable>

    suspend fun isMarkedAsFavourite(productId: Int): Result<Boolean, Throwable>

    fun getFavouriteProducts(): Flow<List<FavouriteProductDetailsEntity>>
}