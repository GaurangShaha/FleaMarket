package com.flea.market.ui.modifier

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.flea.market.ui.preview.FleaMarketPreviews
import com.flea.market.ui.preview.FleaMarketThemePreview

@Composable
@FleaMarketPreviews
private fun ShimmerPreview() {
    FleaMarketThemePreview {
        Box(
            modifier = Modifier
                .size(50.dp)
                .shimmer()
        )
    }
}
