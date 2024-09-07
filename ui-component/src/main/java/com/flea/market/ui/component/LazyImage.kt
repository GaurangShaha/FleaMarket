package com.flea.market.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.flea.market.ui.modifier.shimmer
import com.flea.market.ui.preview.FleaMarketPreviews
import com.flea.market.ui.preview.FleaMarketThemePreview
import com.flea.market.ui.theme.extraColors

@Composable
public fun LazyImage(
    url: String,
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
    contentScale: ContentScale = ContentScale.Crop
) {
    SubcomposeAsyncImage(
        model = url,
        contentDescription = contentDescription,
        modifier = modifier,
        contentScale = contentScale,
        loading = { Box(modifier = Modifier.shimmer()) },
        error = { Box(modifier = Modifier.background(MaterialTheme.extraColors.darkGrey)) }
    )
}

@FleaMarketPreviews
@Composable
private fun LazyImagePreview() {
    FleaMarketThemePreview {
        @Suppress("MaxLineLength")
        LazyImage(
            url = "https://www.gstatic.com/devrel-devsite/prod/v0f868bacf787bf31b228952b4e9f9c852485b2025a1f6f6571309b6d62ea4de2/android/images/lockup.svg",
            modifier = Modifier.size(50.dp)
        )
    }
}
