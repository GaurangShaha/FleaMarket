package com.flea.market.product.ui.details

import building.blocks.ui.preview.FleaMarketPreviews
import building.blocks.ui.preview.FleaMarketThemePreview
import androidx.compose.runtime.Composable
import com.flea.market.product.ui.dummy.data.dummyContent

@Composable
@FleaMarketPreviews
private fun ProductDetailsContentScreenPreview() {
    FleaMarketThemePreview {
        ProductDetailsScreen(uiState = dummyContent, processIntent = {})
    }
}
