package com.flea.market.cart.ui.details.component

import building.blocks.ui.preview.FleaMarketPreviews
import building.blocks.ui.preview.FleaMarketThemePreview
import androidx.compose.runtime.Composable

@Composable
@FleaMarketPreviews
private fun CheckoutButtonPreview() {
    FleaMarketThemePreview {
        CheckoutButton {}
    }
}
