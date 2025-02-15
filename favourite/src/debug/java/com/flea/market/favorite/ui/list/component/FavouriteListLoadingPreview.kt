package com.flea.market.favorite.ui.list.component

import androidx.compose.runtime.Composable
import com.flea.market.ui.preview.FleaMarketPreviews
import com.flea.market.ui.preview.FleaMarketThemePreview

@Composable
@FleaMarketPreviews
internal fun FavouriteListLoadingPreview() {
    FleaMarketThemePreview {
        FavouriteListLoading()
    }
}
