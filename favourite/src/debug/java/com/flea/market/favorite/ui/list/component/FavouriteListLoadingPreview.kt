package com.flea.market.favorite.ui.list.component

import building.blocks.ui.preview.FleaMarketPreviews
import building.blocks.ui.preview.FleaMarketThemePreview
import androidx.compose.runtime.Composable

@Composable
@FleaMarketPreviews
internal fun FavouriteListLoadingPreview() {
    FleaMarketThemePreview {
        FavouriteListLoading()
    }
}
