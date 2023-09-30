package com.flea.market.data.common.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.flea.market.data.cart.local.entity.CartProductDetailsEntity
import com.flea.market.data.cart.local.source.CartLocalSource
import com.flea.market.data.favourite.local.entity.FavouriteProductDetailsEntity
import com.flea.market.data.favourite.local.source.FavouriteLocalSource

@Database(
    entities = [CartProductDetailsEntity::class, FavouriteProductDetailsEntity::class], version = 1
)
internal abstract class FleaMarketDatabase : RoomDatabase() {
    abstract fun cartLocalSource(): CartLocalSource
    abstract fun favouriteLocalSource(): FavouriteLocalSource
}

object DatabaseProvider {
    private var INSTANCE: FleaMarketDatabase? = null

    internal fun getDatabase(context: Context): FleaMarketDatabase {
        val dbName = "app_database"
        return INSTANCE ?: synchronized(this) {
            val instance = Room.databaseBuilder(
                context = context, klass = FleaMarketDatabase::class.java, name = dbName
            ).build()
            INSTANCE = instance
            instance
        }
    }
}