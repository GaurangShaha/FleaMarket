package com.flea.market.favorite.ui.list

import building.blocks.ui.preview.FleaMarketPreviews
import building.blocks.ui.preview.FleaMarketThemePreview
import androidx.compose.runtime.Composable
import com.flea.market.favorite.ui.list.FavouriteListUiState.Loading

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
