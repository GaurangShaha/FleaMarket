package com.flea.market.product.ui.list.component

import building.blocks.ui.preview.FleaMarketPreviews
import building.blocks.ui.preview.FleaMarketThemePreview
import androidx.compose.runtime.Composable
import com.flea.market.product.ui.dummy.data.dummyProductListContent

@FleaMarketPreviews
@Composable
private fun ProductListContentPreview() {
    FleaMarketThemePreview {
        ProductListContent(state = dummyProductListContent, processIntent = {})
    }
}
