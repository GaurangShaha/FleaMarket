package com.flea.market.favorite.ui.list

import android.artisan.ui.preview.FleaMarketPreviews
import android.artisan.ui.preview.FleaMarketThemePreview
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
