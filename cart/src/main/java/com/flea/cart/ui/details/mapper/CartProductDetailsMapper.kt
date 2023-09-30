package com.flea.cart.ui.details.mapper

import com.flea.cart.ui.details.entity.ItemsInCartViewEntity
import com.flea.market.data.cart.local.entity.CartProductDetailsEntity

internal fun CartProductDetailsEntity.toItemsInCartViewEntity() =
    ItemsInCartViewEntity(id, category, image, price, title, quantity)
