package com.flea.market.product.ui.list

import android.artisan.ui.preview.FleaMarketPreviews
import android.artisan.ui.preview.FleaMarketThemePreview
import androidx.compose.runtime.Composable

@FleaMarketPreviews
@Composable
private fun ProductListScreenLoadingPreview() {
    FleaMarketThemePreview {
        ProductListScreen(state = ProductListUiState.Loading, processIntent = {})
    }
}
