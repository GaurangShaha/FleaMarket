package com.flea.market.favorite.ui.list.mapper

import com.flea.market.cart.local.entity.CartProductDetailsEntity
import com.flea.market.favorite.ui.list.entity.FavouriteItemViewEntity

internal fun FavouriteItemViewEntity.toCartProductDetails() =
    CartProductDetailsEntity(
        id = id,
        category = category,
        image = image,
        price = price,
        title = title,
        quantity = 1
    )
