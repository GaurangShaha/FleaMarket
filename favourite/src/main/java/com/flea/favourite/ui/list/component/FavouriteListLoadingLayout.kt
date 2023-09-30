package com.flea.favourite.ui.list.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.flea.common.ui.component.preview.FleaMarketPreview
import com.flea.common.ui.component.preview.FleaMarketThemePreview
import com.flea.common.ui.modifier.shimmer

@Composable
internal fun ProductListLoading() {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(146.dp),
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(bottom = 90.dp, start = 16.dp, end = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(10) {
            Box(
                modifier = Modifier
                    .size(width = 146.dp, height = 250.dp)
                    .clip(MaterialTheme.shapes.large)
                    .shimmer()
            )
        }
    }
}

@Composable
@FleaMarketPreview
internal fun ProductListLoadingPreview() {
    FleaMarketThemePreview {
        ProductListLoading()
    }
}
