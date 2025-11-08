package com.flea.market.favorite.ui.list.component

import building.blocks.ui.preview.FleaMarketPreviews
import building.blocks.ui.preview.FleaMarketThemePreview
import androidx.compose.runtime.Composable
import com.flea.market.favorite.ui.dummy.data.dummyContent

@FleaMarketPreviews
@Composable
private fun FavouriteListContentPreview() {
    FleaMarketThemePreview {
        FavouriteListContent(uiState = dummyContent, processIntent = {})
    }
}
