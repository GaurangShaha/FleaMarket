package com.flea.market.cart.ui.details.mapper

import com.flea.market.cart.local.entity.CartProductDetailsEntity
import com.flea.market.cart.ui.details.entity.ItemsInCartViewEntity

internal fun CartProductDetailsEntity.toItemsInCartViewEntity() =
    ItemsInCartViewEntity(id, category, image, price, title, quantity)
