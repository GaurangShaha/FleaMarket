package com.flea.market.product.ui.list.component

import building.blocks.ui.preview.FleaMarketPreviews
import building.blocks.ui.preview.FleaMarketThemePreview
import androidx.compose.runtime.Composable
import com.flea.market.product.ui.dummy.data.dummyCategoryListWrapper

@FleaMarketPreviews
@Composable
private fun CategorySectionPreview() {
    FleaMarketThemePreview {
        CategorySection(
            categoryListWrapper = dummyCategoryListWrapper,
            selectedCategoryIndex = 0
        ) {}
    }
}
