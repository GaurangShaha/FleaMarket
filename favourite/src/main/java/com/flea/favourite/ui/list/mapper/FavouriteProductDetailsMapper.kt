package com.flea.favourite.ui.list.mapper

import com.flea.favourite.ui.list.entity.FavouriteItemViewEntity
import com.flea.market.data.favourite.local.entity.FavouriteProductDetailsEntity

internal fun List<FavouriteProductDetailsEntity>.toFavouriteItemViewEntity() = map {
    FavouriteItemViewEntity(
        category = it.category,
        description = it.description,
        id = it.id,
        image = it.image,
        price = it.price,
        rating = it.rating,
        title = it.title
    )
}