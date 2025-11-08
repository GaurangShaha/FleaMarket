package com.flea.market.product.ui.details

import building.blocks.ui.preview.FleaMarketPreviews
import building.blocks.ui.preview.FleaMarketThemePreview
import androidx.compose.runtime.Composable

@Composable
@FleaMarketPreviews
private fun ProductDetailsLoadingScreenPreview() {
    FleaMarketThemePreview {
        ProductDetailsScreen(uiState = ProductDetailsUiState.Loading, processIntent = {})
    }
}
