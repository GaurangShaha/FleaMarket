package com.flea.market.product.ui.details.component

import building.blocks.ui.preview.FleaMarketPreviews
import building.blocks.ui.preview.FleaMarketThemePreview
import androidx.compose.runtime.Composable
import com.flea.market.product.ui.dummy.data.dummyContent

@FleaMarketPreviews
@Composable
private fun ProductInformationPreview() {
    FleaMarketThemePreview {
        ProductInformation(dummyContent)
    }
}
