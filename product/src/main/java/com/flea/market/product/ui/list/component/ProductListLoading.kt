package com.flea.market.product.ui.list.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridItemSpan
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.flea.market.ui.modifier.shimmer
import com.flea.market.ui.preview.FleaMarketPreviews
import com.flea.market.ui.preview.FleaMarketThemePreview
import com.flea.market.ui.theme.extraShape

private const val CATEGORY_COUNT = 5
private const val PRODUCT_COUNT = 6
private const val CATEGORY_MIN_WIDTH = 75
private const val CATEGORY_MAX_WIDTH = 150

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun ProductListLoading() {
    LazyVerticalStaggeredGrid(
        verticalItemSpacing = 16.dp,
        columns = StaggeredGridCells.Adaptive(CATEGORY_MAX_WIDTH.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(start = 16.dp, end = 16.dp, bottom = 94.dp)
    ) {
        item(span = StaggeredGridItemSpan.FullLine) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                repeat(CATEGORY_COUNT) {
                    Box(
                        modifier = Modifier
                            .width((CATEGORY_MIN_WIDTH..CATEGORY_MAX_WIDTH).random().dp)
                            .height(30.dp)
                            .clip(MaterialTheme.extraShape.capsuleShape)
                            .shimmer()
                    )
                }
            }
        }

        item {
            Box(
                modifier = Modifier
                    .height(90.dp)
                    .clip(MaterialTheme.shapes.large)
                    .shimmer()
            )
        }

        items(PRODUCT_COUNT) {
            Box(
                modifier = Modifier
                    .size(CATEGORY_MAX_WIDTH.dp, 250.dp)
                    .clip(MaterialTheme.shapes.large)
                    .shimmer()
            )
        }
    }
}

@FleaMarketPreviews
@Composable
private fun ProductListLoadingPreview() {
    FleaMarketThemePreview {
        ProductListLoading()
    }
}
