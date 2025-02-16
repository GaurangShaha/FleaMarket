package com.flea.market.product.ui.details

import android.artisan.ui.preview.FleaMarketPreviews
import android.artisan.ui.preview.FleaMarketThemePreview
import androidx.compose.runtime.Composable

@Composable
@FleaMarketPreviews
private fun ProductDetailsLoadingScreenPreview() {
    FleaMarketThemePreview {
        ProductDetailsScreen(uiState = ProductDetailsUiState.Loading, processIntent = {})
    }
}
