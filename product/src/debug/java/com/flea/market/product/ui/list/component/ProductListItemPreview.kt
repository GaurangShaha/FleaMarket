package com.flea.market.product.ui.list.component

import building.blocks.ui.preview.FleaMarketPreviews
import building.blocks.ui.preview.FleaMarketThemePreview
import androidx.compose.runtime.Composable
import com.flea.market.product.ui.dummy.data.dummyProductList

@FleaMarketPreviews
@Composable
private fun ProductListItemPreview() {
    FleaMarketThemePreview {
        ProductListItem(productDetails = dummyProductList.first()) {}
    }
}
