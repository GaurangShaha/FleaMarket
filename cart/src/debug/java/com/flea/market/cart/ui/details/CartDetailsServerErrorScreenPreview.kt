package com.flea.market.cart.ui.details

import androidx.compose.runtime.Composable
import com.flea.market.cart.ui.details.CartDetailsUiState.Error
import com.flea.market.ui.preview.FleaMarketPreviews
import com.flea.market.ui.preview.FleaMarketThemePreview

@Composable
@FleaMarketPreviews
private fun CartDetailsServerErrorScreenPreview() {
    FleaMarketThemePreview {
        CartDetailsScreen(
            uiState = Error(NullPointerException("Null id")),
            processIntent = {}
        )
    }
}
