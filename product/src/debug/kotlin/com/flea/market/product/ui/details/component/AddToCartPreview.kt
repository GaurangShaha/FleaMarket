package com.flea.market.product.ui.details.component

import androidx.compose.runtime.Composable
import com.flea.market.product.ui.details.component.AddToCart
import com.flea.market.ui.component.ButtonState
import com.flea.market.ui.preview.FleaMarketPreviews
import com.flea.market.ui.preview.FleaMarketThemePreview

@Composable
@FleaMarketPreviews
private fun AddToCartPreview(){
    FleaMarketThemePreview{
        AddToCart(buttonState = ButtonState.Initial) { }
    }
}