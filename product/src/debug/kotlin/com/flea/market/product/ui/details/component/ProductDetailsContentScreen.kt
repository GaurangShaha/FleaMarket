package com.flea.market.product.ui.details.component

import androidx.compose.runtime.Composable
import com.flea.market.product.ui.details.component.ProductDetailsContent
import com.flea.market.product.ui.dummy.data.dummyContent
import com.flea.market.ui.preview.FleaMarketPreviews
import com.flea.market.ui.preview.FleaMarketThemePreview

@FleaMarketPreviews
@Composable
private fun ProductDetailsContentScreen() {
    FleaMarketThemePreview {
        ProductDetailsContent(
            state = dummyContent
        ) {}
    }
}