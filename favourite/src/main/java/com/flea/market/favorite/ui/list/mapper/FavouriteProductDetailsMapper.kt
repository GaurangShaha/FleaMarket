package com.flea.market.favorite.ui.list.mapper

import com.flea.market.favorite.ui.list.entity.FavouriteItemViewEntity
import com.flea.market.favourite.local.entity.FavouriteProductDetailsEntity

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

internal fun FavouriteItemViewEntity.toFavouriteProductDetailsEntity(): FavouriteProductDetailsEntity {
    return FavouriteProductDetailsEntity(
        id = id,
        category = category,
        image = image,
        price = price,
        title = title,
        description = description,
        rating = rating
    )
}
