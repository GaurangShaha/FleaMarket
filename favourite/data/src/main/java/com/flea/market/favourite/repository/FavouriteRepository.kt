package com.flea.market.favourite.repository

import com.flea.market.favourite.local.entity.FavouriteProductDetailsEntity
import com.flea.market.foundation.model.Result
import kotlinx.coroutines.flow.Flow

public interface FavouriteRepository {
    public suspend fun markAsFavourite(
        favouriteProductDetailsEntity: FavouriteProductDetailsEntity
    ): Result<Unit>

    public suspend fun removeFromFavourite(productId: Int): Result<Unit>

    public suspend fun isMarkedAsFavourite(productId: Int): Result<Boolean>

    public fun getFavouriteProductsStream(): Flow<List<FavouriteProductDetailsEntity>>
}
