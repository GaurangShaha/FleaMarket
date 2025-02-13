package com.flea.market.ui.component

import androidx.compose.runtime.Composable
import com.flea.market.ui.preview.FleaMarketPreviews
import com.flea.market.ui.preview.FleaMarketThemePreview

@Composable
@FleaMarketPreviews
private fun StepperPreview() {
    FleaMarketThemePreview {
        Stepper(quantity = 0, onIncreaseQuantity = {}, onDecreaseQuantity = { })
    }
}
