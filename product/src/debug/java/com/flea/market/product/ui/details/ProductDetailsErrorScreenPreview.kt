package com.flea.market.product.ui.details

import building.blocks.foundation.model.InternetDisconnectionException
import building.blocks.ui.preview.FleaMarketPreviews
import building.blocks.ui.preview.FleaMarketThemePreview
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
