package com.flea.market.product.ui.details.component

import android.artisan.ui.compositionlocal.LocalWindowSizeClass
import android.artisan.ui.modifier.shimmer
import android.artisan.ui.theme.extraShape
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.windowsizeclass.WindowHeightSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

@Composable
internal fun ProductDetailsLoading() {
    if (LocalWindowSizeClass.current.widthSizeClass == WindowWidthSizeClass.Compact ||
        LocalWindowSizeClass.current.heightSizeClass == WindowHeightSizeClass.Expanded
    ) {
        LoadingCompactScreen()
    } else {
        LoadingMediumAndExpandedScreen()
    }
}

private const val LOADING_BOX_ASPECT_RATIO = 0.9f

@Composable
internal fun LoadingMediumAndExpandedScreen() {
    Row {
        Box(
            Modifier
                .fillMaxHeight()
                .aspectRatio(LOADING_BOX_ASPECT_RATIO)
        ) {
            ProductImagesShimmer()
        }

        Column(Modifier.weight(1f)) {
            ProductInfoAndCheckout()
        }
    }
}

@Composable
private fun LoadingCompactScreen() {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        ProductImagesShimmer()

        ProductInfoAndCheckout()
    }
}

@Composable
private fun ColumnScope.ProductInfoAndCheckout() {
    Box(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .weight(1f)
            .clip(MaterialTheme.shapes.medium)
            .shimmer()
    )

    Box(
        modifier = Modifier
            .padding(bottom = 16.dp, start = 16.dp, end = 16.dp)
            .fillMaxWidth()
            .height(46.dp)
            .clip(MaterialTheme.extraShape.capsuleShape)
            .shimmer()
    )
}

@Composable
private fun ProductImagesShimmer() {
    Box(
        Modifier
            .fillMaxWidth()
            .aspectRatio(LOADING_BOX_ASPECT_RATIO)
            .shimmer()
    )
}
