package com.flea.market.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.flea.market.cart.data.local.entity.CartProductDetailsEntity
import com.flea.market.cart.data.local.source.CartLocalSource
import com.flea.market.favourite.local.entity.FavouriteProductDetailsEntity
import com.flea.market.favourite.local.source.FavouriteLocalSource

@Database(
    entities = [CartProductDetailsEntity::class, FavouriteProductDetailsEntity::class],
    version = 1
)
internal abstract class FleaMarketDatabase : RoomDatabase() {
    abstract fun cartLocalSource(): CartLocalSource
    abstract fun favouriteLocalSource(): FavouriteLocalSource
}

internal object DatabaseProvider {
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
