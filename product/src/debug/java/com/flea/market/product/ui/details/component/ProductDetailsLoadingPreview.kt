package com.flea.market.product.ui.details.component

import building.blocks.ui.preview.FleaMarketPreviews
import building.blocks.ui.preview.FleaMarketThemePreview
import androidx.compose.runtime.Composable

@FleaMarketPreviews
@Composable
private fun ProductDetailsLoadingPreview() {
    FleaMarketThemePreview {
        ProductDetailsLoading()
    }
}
