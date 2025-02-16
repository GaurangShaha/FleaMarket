package com.flea.market.favorite.ui.list

import android.artisan.ui.preview.FleaMarketPreviews
import android.artisan.ui.preview.FleaMarketThemePreview
import androidx.compose.runtime.Composable

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
