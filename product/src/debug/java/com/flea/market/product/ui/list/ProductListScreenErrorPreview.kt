package com.flea.market.product.ui.list

import android.artisan.foundation.model.InternetDisconnectionException
import android.artisan.ui.preview.FleaMarketPreviews
import android.artisan.ui.preview.FleaMarketThemePreview
import androidx.compose.runtime.Composable

@FleaMarketPreviews
@Composable
private fun ProductListScreenErrorPreview() {
    FleaMarketThemePreview {
        ProductListScreen(
            state = ProductListUiState.Error(throwable = InternetDisconnectionException),
            processIntent = {}
        )
    }
}
