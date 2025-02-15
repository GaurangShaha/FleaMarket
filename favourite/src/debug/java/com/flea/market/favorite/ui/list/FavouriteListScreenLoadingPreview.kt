package com.flea.market.favorite.ui.list

import androidx.compose.runtime.Composable
import com.flea.market.favorite.ui.list.FavouriteListUiState.Loading
import com.flea.market.ui.preview.FleaMarketPreviews
import com.flea.market.ui.preview.FleaMarketThemePreview

@FleaMarketPreviews
@Composable
private fun FavouriteListScreenLoadingPreview() {
    FleaMarketThemePreview {
        FavouriteListScreen(
            uiState = Loading,
            processIntent = {}
        )
    }
}
