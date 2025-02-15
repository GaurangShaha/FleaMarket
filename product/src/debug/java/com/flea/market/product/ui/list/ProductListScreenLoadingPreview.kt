package com.flea.market.product.ui.list

import androidx.compose.runtime.Composable
import com.flea.market.ui.preview.FleaMarketPreviews
import com.flea.market.ui.preview.FleaMarketThemePreview

@FleaMarketPreviews
@Composable
private fun ProductListScreenLoadingPreview() {
    FleaMarketThemePreview {
        ProductListScreen(state = ProductListUiState.Loading, processIntent = {})
    }
}
