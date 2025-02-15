package com.flea.market.favorite.ui.list

import androidx.compose.runtime.Composable
import com.flea.market.foundation.model.InternetDisconnectionException
import com.flea.market.ui.preview.FleaMarketPreviews
import com.flea.market.ui.preview.FleaMarketThemePreview

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
