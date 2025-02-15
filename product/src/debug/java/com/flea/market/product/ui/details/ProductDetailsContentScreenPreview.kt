package com.flea.market.product.ui.details

import androidx.compose.runtime.Composable
import com.flea.market.product.ui.dummy.data.dummyContent
import com.flea.market.ui.preview.FleaMarketPreviews
import com.flea.market.ui.preview.FleaMarketThemePreview

@Composable
@FleaMarketPreviews
private fun ProductDetailsContentScreenPreview() {
    FleaMarketThemePreview {
        ProductDetailsScreen(uiState = dummyContent, processIntent = {})
    }
}
