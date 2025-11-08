package com.flea.market.favorite.ui.list

import building.blocks.foundation.model.InternetDisconnectionException
import building.blocks.ui.preview.FleaMarketPreviews
import building.blocks.ui.preview.FleaMarketThemePreview
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
