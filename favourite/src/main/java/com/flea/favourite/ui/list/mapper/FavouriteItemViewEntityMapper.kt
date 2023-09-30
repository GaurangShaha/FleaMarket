package com.flea.favourite.ui.list.mapper

import com.flea.favourite.ui.list.entity.FavouriteItemViewEntity
import com.flea.market.data.cart.local.entity.CartProductDetailsEntity

internal fun FavouriteItemViewEntity.toCartProductDetails() = CartProductDetailsEntity(
    id = id, category = category, image = image, price = price, title = title, quantity = 1
)
