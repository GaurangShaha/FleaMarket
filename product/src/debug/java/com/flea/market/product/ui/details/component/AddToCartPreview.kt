package com.flea.market.product.ui.details.component

import building.blocks.ui.component.ButtonState
import building.blocks.ui.preview.FleaMarketPreviews
import building.blocks.ui.preview.FleaMarketThemePreview
import androidx.compose.runtime.Composable

@Composable
@FleaMarketPreviews
private fun AddToCartPreview() {
    FleaMarketThemePreview {
        AddToCart(buttonState = ButtonState.Initial) { }
    }
}
