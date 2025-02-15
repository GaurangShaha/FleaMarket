package com.flea.market.ui.component

import androidx.compose.runtime.Composable
import com.flea.market.ui.preview.FleaMarketPreviews
import com.flea.market.ui.preview.FleaMarketThemePreview

@FleaMarketPreviews
@Composable
public fun FMButtonPreview() {
    FleaMarketThemePreview {
        FMButton("Click me..") {}
    }
}

@FleaMarketPreviews
@Composable
public fun FMOutlinedButtonPreview() {
    FleaMarketThemePreview {
        FMOutlinedButton("Click me..") {}
    }
}
