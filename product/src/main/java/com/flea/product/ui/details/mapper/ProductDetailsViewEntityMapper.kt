package com.flea.product.ui.details.mapper

import com.flea.market.data.cart.local.entity.CartProductDetailsEntity
import com.flea.market.data.favourite.local.entity.FavouriteProductDetailsEntity
import com.flea.product.ui.common.entity.ProductDetailsViewEntity

internal fun ProductDetailsViewEntity.toCartProductDetails() = CartProductDetailsEntity(
    id = id,
    category = category,
    image = imageList.first(),
    price = price,
    title = title,
    quantity = 1
)

internal fun ProductDetailsViewEntity.toFavouriteProductDetails() = FavouriteProductDetailsEntity(
    id = id,
    category = category,
    image = imageList.first(),
    price = price,
    title = title,
    rating = rate,
    description = description
)