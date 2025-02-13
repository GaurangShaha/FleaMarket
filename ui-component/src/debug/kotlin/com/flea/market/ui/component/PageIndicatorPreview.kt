package com.flea.market.ui.component

import androidx.compose.runtime.Composable
import com.flea.market.ui.preview.FleaMarketPreviews
import com.flea.market.ui.preview.FleaMarketThemePreview

@FleaMarketPreviews
@Composable
private fun PageIndicatorPreview() {
    FleaMarketThemePreview {
        PageIndicator(totalPages = 5, currentPage = 1)
    }
}