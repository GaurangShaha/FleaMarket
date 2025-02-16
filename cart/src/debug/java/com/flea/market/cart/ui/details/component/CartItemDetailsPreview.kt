package com.flea.market.cart.ui.details.component

import android.artisan.ui.preview.FleaMarketPreviews
import android.artisan.ui.preview.FleaMarketThemePreview
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
