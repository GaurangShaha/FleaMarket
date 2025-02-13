package com.flea.market.favorite.ui.list.component

import androidx.compose.runtime.Composable
import com.flea.market.favorite.ui.list.component.FavouriteListContent
import com.flea.market.favorite.ui.dummy.data.dummyContent
import com.flea.market.ui.preview.FleaMarketPreviews
import com.flea.market.ui.preview.FleaMarketThemePreview

@FleaMarketPreviews
@Composable
private fun FavouriteListContentPreview() {
    FleaMarketThemePreview {
        FavouriteListContent(uiState = dummyContent, onHandleIntent = {})
    }
}
