package com.flea.market.cart.ui.details

import android.artisan.ui.preview.FleaMarketPreviews
import android.artisan.ui.preview.FleaMarketThemePreview
import androidx.compose.runtime.Composable
import com.flea.market.cart.ui.details.CartDetailsUiState.Empty

@Composable
@FleaMarketPreviews
private fun CartDetailsEmptyScreenPreview() {
    FleaMarketThemePreview {
        CartDetailsScreen(uiState = Empty, processIntent = {})
    }
}
