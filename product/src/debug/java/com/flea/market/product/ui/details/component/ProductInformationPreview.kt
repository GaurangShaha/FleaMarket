package com.flea.market.product.ui.details.component

import android.artisan.ui.preview.FleaMarketPreviews
import android.artisan.ui.preview.FleaMarketThemePreview
import androidx.compose.runtime.Composable
import com.flea.market.product.ui.dummy.data.dummyContent

@FleaMarketPreviews
@Composable
private fun ProductInformationPreview() {
    FleaMarketThemePreview {
        ProductInformation(dummyContent)
    }
}
