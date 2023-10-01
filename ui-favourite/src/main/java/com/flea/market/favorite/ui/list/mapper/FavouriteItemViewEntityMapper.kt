package com.flea.market.favorite.ui.list.mapper

import com.flea.market.favorite.ui.list.entity.FavouriteItemViewEntity
import com.flea.market.cart.local.entity.CartProductDetailsEntity

internal fun FavouriteItemViewEntity.toCartProductDetails() =
    com.flea.market.cart.local.entity.CartProductDetailsEntity(
        id = id, category = category, image = image, price = price, title = title, quantity = 1
    )
