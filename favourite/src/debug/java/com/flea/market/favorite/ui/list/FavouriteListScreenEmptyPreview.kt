package com.flea.market.favorite.ui.list

import androidx.compose.runtime.Composable
import com.flea.market.ui.preview.FleaMarketPreviews
import com.flea.market.ui.preview.FleaMarketThemePreview

@FleaMarketPreviews
@Composable
private fun FavouriteListScreenEmptyPreview() {
    FleaMarketThemePreview {
        FavouriteListScreen(
            uiState = FavouriteListUiState.Empty,
            processIntent = {}
        )
    }
}
