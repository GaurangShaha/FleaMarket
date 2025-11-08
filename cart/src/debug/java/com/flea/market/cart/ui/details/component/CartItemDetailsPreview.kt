package com.flea.market.cart.ui.details.component

import building.blocks.ui.preview.FleaMarketPreviews
import building.blocks.ui.preview.FleaMarketThemePreview
import androidx.compose.runtime.Composable
import com.flea.market.cart.ui.dummy.data.dummyItemsInCartList

@Composable
@FleaMarketPreviews
private fun CartItemDetailsPreview() {
    FleaMarketThemePreview {
        CartItemProductDetails(
            itemsInCart = dummyItemsInCartList.first(),
            onDecreaseQuantity = {},
            onIncreaseQuantity = {},
            onRemoveFromCart = {}
        ) {}
    }
}
