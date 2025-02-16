package com.flea.market.product.ui.list

import android.artisan.ui.preview.FleaMarketPreviews
import android.artisan.ui.preview.FleaMarketThemePreview
import androidx.compose.runtime.Composable
import com.flea.market.product.ui.dummy.data.dummyProductListContent

@FleaMarketPreviews
@Composable
private fun ProductListScreenContentPreview() {
    FleaMarketThemePreview {
        ProductListScreen(state = dummyProductListContent, processIntent = {})
    }
}
