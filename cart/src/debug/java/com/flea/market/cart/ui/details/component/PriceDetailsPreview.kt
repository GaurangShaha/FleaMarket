package com.flea.market.cart.ui.details.component

import android.artisan.ui.preview.FleaMarketPreviews
import android.artisan.ui.preview.FleaMarketThemePreview
import androidx.compose.runtime.Composable
import com.flea.market.cart.ui.details.entity.PriceDetailsViewEntity

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
