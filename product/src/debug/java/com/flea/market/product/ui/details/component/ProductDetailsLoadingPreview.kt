package com.flea.market.product.ui.details.component

import android.artisan.ui.preview.FleaMarketPreviews
import android.artisan.ui.preview.FleaMarketThemePreview
import androidx.compose.runtime.Composable

@FleaMarketPreviews
@Composable
private fun ProductDetailsLoadingPreview() {
    FleaMarketThemePreview {
        ProductDetailsLoading()
    }
}
