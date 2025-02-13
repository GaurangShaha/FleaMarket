package com.flea.market.product.ui.details

import androidx.compose.runtime.Composable
import com.flea.market.ui.preview.FleaMarketPreviews
import com.flea.market.ui.preview.FleaMarketThemePreview

@Composable
@FleaMarketPreviews
private fun ProductDetailsLoadingScreenPreview() {
    FleaMarketThemePreview {
        ProductDetailsScreen(uiState = ProductDetailsUiState.Loading, onHandleIntent = {})
    }
}