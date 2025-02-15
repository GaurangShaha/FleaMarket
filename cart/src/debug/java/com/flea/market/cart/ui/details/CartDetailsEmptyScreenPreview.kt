package com.flea.market.cart.ui.details

import androidx.compose.runtime.Composable
import com.flea.market.cart.ui.details.CartDetailsUiState.Empty
import com.flea.market.ui.preview.FleaMarketPreviews
import com.flea.market.ui.preview.FleaMarketThemePreview

@Composable
@FleaMarketPreviews
private fun CartDetailsEmptyScreenPreview() {
    FleaMarketThemePreview {
        CartDetailsScreen(uiState = Empty, processIntent = {})
    }
}
