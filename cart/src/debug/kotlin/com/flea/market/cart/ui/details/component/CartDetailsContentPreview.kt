package com.flea.market.cart.ui.details.component

import androidx.compose.runtime.Composable
import com.flea.market.cart.ui.dummy.data.dummyContent
import com.flea.market.ui.preview.FleaMarketPreviews
import com.flea.market.ui.preview.FleaMarketThemePreview

@Composable
@FleaMarketPreviews
private fun CartDetailsContentPreview() {
    FleaMarketThemePreview {
        CartDetailsContent(uiState = dummyContent, onHandleIntent = {})
    }
}