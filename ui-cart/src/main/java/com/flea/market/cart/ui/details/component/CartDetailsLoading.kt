package com.flea.market.cart.ui.details.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.flea.market.ui.compositionlocal.LocalWindowSizeClass
import com.flea.market.ui.modifier.shimmer
import com.flea.market.ui.preview.FleaMarketPreview
import com.flea.market.ui.preview.FleaMarketThemePreview
import com.flea.market.ui.theme.extraShape

@Composable
internal fun CartDetailsLoading() {
    if (LocalWindowSizeClass.current.widthSizeClass == WindowWidthSizeClass.Compact) {
        LoadingCompactScreen()
    } else {
        LoadingMediumAndExpandedScreen()
    }
}

@Composable
private fun LoadingMediumAndExpandedScreen() {
    Row(modifier = Modifier.padding(8.dp)) {
        Column(Modifier.weight(0.4f)) {
            ProductInfoAndCheckoutShimmer()
        }

        Spacer(modifier = Modifier.size(24.dp))

        Column(Modifier.weight(0.6f)) {
            ProductListShimmer()
        }
    }
}

@Composable
private fun LoadingCompactScreen() {
    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        ProductInfoAndCheckoutShimmer()

        Spacer(modifier = Modifier.size(24.dp))

        ProductListShimmer()
    }
}

@Composable
private fun ProductListShimmer() {
    repeat(5) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .clip(MaterialTheme.shapes.medium)
                .shimmer()
        )

        Spacer(modifier = Modifier.size(24.dp))
    }
}

@Composable
private fun ProductInfoAndCheckoutShimmer() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(210.dp)
            .clip(MaterialTheme.shapes.medium)
            .shimmer()
    )

    Spacer(modifier = Modifier.size(24.dp))

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(46.dp)
            .clip(MaterialTheme.extraShape.capsuleShape)
            .shimmer()
    )
}

@Composable
@FleaMarketPreview
private fun CartDetailsLoadingPreview() {
    FleaMarketThemePreview {
        CartDetailsLoading()
    }
}