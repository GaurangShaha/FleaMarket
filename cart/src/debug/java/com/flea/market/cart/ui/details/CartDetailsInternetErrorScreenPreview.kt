package com.flea.market.cart.ui.details

import android.artisan.foundation.model.InternetDisconnectionException
import android.artisan.ui.preview.FleaMarketPreviews
import android.artisan.ui.preview.FleaMarketThemePreview
import androidx.compose.runtime.Composable
import com.flea.market.cart.ui.details.CartDetailsUiState.Error

@Composable
@FleaMarketPreviews
private fun CartDetailsInternetErrorScreenPreview() {
    FleaMarketThemePreview {
        CartDetailsScreen(uiState = Error(InternetDisconnectionException), processIntent = {})
    }
}
