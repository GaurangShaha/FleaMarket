package com.flea.market.cart.ui.details

import android.artisan.ui.preview.FleaMarketPreviews
import android.artisan.ui.preview.FleaMarketThemePreview
import androidx.compose.runtime.Composable
import com.flea.market.cart.ui.dummy.data.dummyContent

@Composable
@FleaMarketPreviews
private fun CartDetailsContentScreenPreview() {
    FleaMarketThemePreview {
        CartDetailsScreen(uiState = dummyContent, processIntent = {})
    }
}
