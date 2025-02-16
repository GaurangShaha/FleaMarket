package com.flea.market.cart.ui.details

import android.artisan.ui.preview.FleaMarketPreviews
import android.artisan.ui.preview.FleaMarketThemePreview
import androidx.compose.runtime.Composable
import com.flea.market.cart.ui.details.CartDetailsUiState.Loading

@Composable
@FleaMarketPreviews
private fun CartDetailsLoadingScreenPreview() {
    FleaMarketThemePreview {
        CartDetailsScreen(uiState = Loading, processIntent = {})
    }
}
