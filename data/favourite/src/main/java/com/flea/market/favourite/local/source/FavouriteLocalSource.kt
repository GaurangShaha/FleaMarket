package com.flea.market.favourite.local.source

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.flea.market.favourite.local.entity.FavouriteProductDetailsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavouriteLocalSource {
    @Query("SELECT * FROM favouriteProductDetailsEntity")
    fun getFavouriteProductsStream(): Flow<List<FavouriteProductDetailsEntity>>

    @Insert
    suspend fun addFavouriteProduct(favouriteProductDetailsEntity: FavouriteProductDetailsEntity)

    @Query("SELECT * FROM favouriteProductDetailsEntity WHERE id= :productId")
    suspend fun getFavouriteProductById(productId: Int): FavouriteProductDetailsEntity?

    @Query("DELETE FROM favouriteProductDetailsEntity WHERE id = :productId")
    suspend fun removeFavouriteProduct(productId: Int)
}
