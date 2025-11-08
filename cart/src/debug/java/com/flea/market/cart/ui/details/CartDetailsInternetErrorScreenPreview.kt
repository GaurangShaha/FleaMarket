package com.flea.market.cart.ui.details

import building.blocks.foundation.model.InternetDisconnectionException
import building.blocks.ui.preview.FleaMarketPreviews
import building.blocks.ui.preview.FleaMarketThemePreview
import androidx.compose.runtime.Composable
import com.flea.market.cart.ui.details.CartDetailsUiState.Error

@Composable
@FleaMarketPreviews
private fun CartDetailsInternetErrorScreenPreview() {
    FleaMarketThemePreview {
        CartDetailsScreen(uiState = Error(InternetDisconnectionException), processIntent = {})
    }
}
