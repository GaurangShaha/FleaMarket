package com.flea.market.favorite.ui.list.component

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
import com.flea.market.ui.modifier.shimmer

private const val PRODUCT_COUNT = 10

@Composable
internal fun FavouriteListLoading() {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(146.dp),
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(bottom = 90.dp, start = 16.dp, end = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(PRODUCT_COUNT) {
            Box(
                modifier = Modifier
                    .size(width = 146.dp, height = 250.dp)
                    .clip(MaterialTheme.shapes.large)
                    .shimmer()
            )
        }
    }
}
