package com.flea.market.favorite.ui.list

import android.artisan.foundation.model.InternetDisconnectionException
import android.artisan.ui.preview.FleaMarketPreviews
import android.artisan.ui.preview.FleaMarketThemePreview
import androidx.compose.runtime.Composable

@FleaMarketPreviews
@Composable
private fun FavouriteListScreenErrorPreview() {
    FleaMarketThemePreview {
        FavouriteListScreen(
            uiState = FavouriteListUiState.Error(InternetDisconnectionException),
            processIntent = {}
        )
    }
}
