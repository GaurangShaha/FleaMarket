package com.flea.market.cart.ui.details

import building.blocks.ui.preview.FleaMarketPreviews
import building.blocks.ui.preview.FleaMarketThemePreview
import androidx.compose.runtime.Composable
import com.flea.market.cart.ui.details.CartDetailsUiState.Error

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
