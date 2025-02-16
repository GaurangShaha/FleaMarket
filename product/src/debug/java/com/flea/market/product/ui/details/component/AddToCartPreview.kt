package com.flea.market.product.ui.details.component

import android.artisan.ui.component.ButtonState
import android.artisan.ui.preview.FleaMarketPreviews
import android.artisan.ui.preview.FleaMarketThemePreview
import androidx.compose.runtime.Composable

@Composable
@FleaMarketPreviews
private fun AddToCartPreview() {
    FleaMarketThemePreview {
        AddToCart(buttonState = ButtonState.Initial) { }
    }
}
