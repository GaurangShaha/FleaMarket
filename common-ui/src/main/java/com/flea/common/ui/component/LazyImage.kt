package com.flea.common.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.flea.common.ui.component.preview.FleaMarketPreview
import com.flea.common.ui.component.preview.FleaMarketThemePreview
import com.flea.common.ui.modifier.shimmer
import com.flea.common.ui.theme.extraColors

@Composable
fun LazyImage(
    modifier: Modifier = Modifier,
    url: String,
    contentDescription: String? = null,
    contentScale: ContentScale = ContentScale.Crop
) {
    SubcomposeAsyncImage(model = url,
        contentDescription = contentDescription,
        modifier = modifier,
        contentScale = contentScale,
        loading = { Box(modifier = Modifier.shimmer()) },
        error = { Box(modifier = Modifier.background(MaterialTheme.extraColors.darkGrey)) })
}

@FleaMarketPreview
@Composable
fun LazyImagePreview() {
    FleaMarketThemePreview {
        LazyImage(Modifier.size(50.dp), url = "")
    }
}