package com.flea.market.product.ui.details

import android.artisan.foundation.model.InternetDisconnectionException
import android.artisan.ui.preview.FleaMarketPreviews
import android.artisan.ui.preview.FleaMarketThemePreview
import androidx.compose.runtime.Composable

@Composable
@FleaMarketPreviews
private fun ProductDetailsErrorScreenPreview() {
    FleaMarketThemePreview {
        ProductDetailsScreen(
            uiState = ProductDetailsUiState.Error(InternetDisconnectionException),
            processIntent = {}
        )
    }
}
