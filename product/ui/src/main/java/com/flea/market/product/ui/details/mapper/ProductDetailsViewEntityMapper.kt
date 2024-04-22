package com.flea.market.product.ui.details.mapper

import com.flea.market.cart.data.local.entity.CartProductDetailsEntity
import com.flea.market.favourite.local.entity.FavouriteProductDetailsEntity

internal fun com.flea.market.product.ui.common.entity.ProductDetailsViewEntity.toCartProductDetails() =
    CartProductDetailsEntity(
        id = id,
        category = category,
        image = imageList.first(),
        price = price,
        title = title,
        quantity = 1
    )

internal fun com.flea.market.product.ui.common.entity.ProductDetailsViewEntity.toFavouriteProductDetails() =
    FavouriteProductDetailsEntity(
        id = id,
        category = category,
        image = imageList.first(),
        price = price,
        title = title,
        rating = rate,
        description = description
    )
