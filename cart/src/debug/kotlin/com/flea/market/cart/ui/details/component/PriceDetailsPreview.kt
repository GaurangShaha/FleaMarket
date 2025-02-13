package com.flea.market.cart.ui.details.component

import androidx.compose.runtime.Composable
import com.flea.market.cart.ui.details.entity.PriceDetailsViewEntity
import com.flea.market.ui.preview.FleaMarketPreviews
import com.flea.market.ui.preview.FleaMarketThemePreview

@Composable
@FleaMarketPreviews
private fun PriceDetailsPreview() {
    FleaMarketThemePreview {
        PriceDetails(
            priceDetails = PriceDetailsViewEntity(
                subTotal = 100.toDouble(),
                discount = 10.toDouble(),
                tax = 12.toDouble(),
                totalPayable = 102.toDouble()
            )
        )
    }
}