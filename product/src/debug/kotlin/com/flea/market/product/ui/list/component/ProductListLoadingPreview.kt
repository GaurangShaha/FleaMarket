package com.flea.market.product.ui.list.component

import androidx.compose.runtime.Composable
import com.flea.market.ui.preview.FleaMarketPreviews
import com.flea.market.ui.preview.FleaMarketThemePreview

@FleaMarketPreviews
@Composable
private fun ProductListLoadingPreview() {
    FleaMarketThemePreview {
        ProductListLoading()
    }
}