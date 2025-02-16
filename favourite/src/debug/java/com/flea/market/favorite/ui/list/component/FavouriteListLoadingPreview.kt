package com.flea.market.favorite.ui.list.component

import android.artisan.ui.preview.FleaMarketPreviews
import android.artisan.ui.preview.FleaMarketThemePreview
import androidx.compose.runtime.Composable

@Composable
@FleaMarketPreviews
internal fun FavouriteListLoadingPreview() {
    FleaMarketThemePreview {
        FavouriteListLoading()
    }
}
