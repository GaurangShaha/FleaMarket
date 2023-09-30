package com.flea.market.data.favourite.local.source

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.flea.market.data.favourite.local.entity.FavouriteProductDetailsEntity
import kotlinx.coroutines.flow.Flow

@Dao
internal interface FavouriteLocalSource {
    @Query("SELECT * FROM favouriteProductDetailsEntity")
    fun getFavouriteProducts(): Flow<List<FavouriteProductDetailsEntity>>

    @Insert
    fun addFavouriteProduct(favouriteProductDetailsEntity: FavouriteProductDetailsEntity)

    @Query("SELECT * FROM favouriteProductDetailsEntity WHERE id= :productId")
    fun getFavouriteProductById(productId: Int): FavouriteProductDetailsEntity?

    @Query("DELETE FROM favouriteProductDetailsEntity WHERE id = :productId")
    fun removeFavouriteProduct(productId: Int)
}