package com.flea.market.product.ui.details

import android.artisan.ui.preview.FleaMarketPreviews
import android.artisan.ui.preview.FleaMarketThemePreview
import androidx.compose.runtime.Composable
import com.flea.market.product.ui.dummy.data.dummyContent

@Composable
@FleaMarketPreviews
private fun ProductDetailsContentScreenPreview() {
    FleaMarketThemePreview {
        ProductDetailsScreen(uiState = dummyContent, processIntent = {})
    }
}
