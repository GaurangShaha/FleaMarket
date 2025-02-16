package com.flea.market.favorite.ui.list.component

import android.artisan.ui.preview.FleaMarketPreviews
import android.artisan.ui.preview.FleaMarketThemePreview
import androidx.compose.runtime.Composable
import com.flea.market.favorite.ui.dummy.data.dummyContent

@FleaMarketPreviews
@Composable
private fun FavouriteListContentPreview() {
    FleaMarketThemePreview {
        FavouriteListContent(uiState = dummyContent, processIntent = {})
    }
}
