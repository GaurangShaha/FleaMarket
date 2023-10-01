package com.flea.market.product.ui.list.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridItemSpan
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.flea.market.ui.modifier.shimmer
import com.flea.market.ui.preview.FleaMarketPreview
import com.flea.market.ui.preview.FleaMarketThemePreview
import com.flea.market.ui.theme.extraShape
import java.util.*

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun ProductListLoading() {
    LazyVerticalStaggeredGrid(
        verticalItemSpacing = 16.dp,
        columns = StaggeredGridCells.Adaptive(150.dp),
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
                repeat(5) {
                    Box(
                        modifier = Modifier
                            .width((75..150).random().dp)
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

        items(6) {
            Box(
                modifier = Modifier
                    .size(150.dp, 250.dp)
                    .clip(MaterialTheme.shapes.large)
                    .shimmer()
            )
        }
    }
}

@FleaMarketPreview
@Composable
fun ProductListLoadingPreview() {
    FleaMarketThemePreview {
        ProductListLoading()
    }
}