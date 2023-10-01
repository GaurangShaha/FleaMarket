package com.flea.market.cart.ui.details.mapper

import com.flea.market.cart.ui.details.entity.ItemsInCartViewEntity
import com.flea.market.cart.local.entity.CartProductDetailsEntity

internal fun com.flea.market.cart.local.entity.CartProductDetailsEntity.toItemsInCartViewEntity() =
    ItemsInCartViewEntity(id, category, image, price, title, quantity)
