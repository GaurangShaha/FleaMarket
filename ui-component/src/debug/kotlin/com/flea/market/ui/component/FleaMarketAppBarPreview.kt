package com.flea.market.ui.component

import androidx.compose.foundation.background
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import com.flea.market.ui.preview.FleaMarketPreviews
import com.flea.market.ui.preview.FleaMarketThemePreview
import com.flea.market.ui.theme.extraColors

@FleaMarketPreviews
@Composable
private fun FleaMarketAppBarPreview() {
    FleaMarketThemePreview {
        FleaMarketAppBar(title = R.string.retry)
    }
}

@FleaMarketPreviews
@Composable
private fun FleaMarketAppBarNavigationItemPreview() {
    FleaMarketThemePreview {
        FleaMarketAppBar(title = R.string.retry, navigationIcon = Icons.AutoMirrored.Filled.ArrowBack to {})
    }
}

@FleaMarketPreviews
@Composable
private fun FleaMarketAppBarActionIconPreview() {
    FleaMarketThemePreview {
        FleaMarketAppBar(
            title = R.string.retry,
            navigationIcon = Icons.AutoMirrored.Filled.ArrowBack to {},
            actionItems = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null
                )
            }
        )
    }
}

@FleaMarketPreviews
@Composable
private fun FleaMarketAppBarWithScrimPreview() {
    FleaMarketThemePreview {
        FleaMarketAppBar(
            title = R.string.retry,
            modifier = Modifier.background(Brush.verticalGradient(MaterialTheme.extraColors.scrimColor)),
            navigationIcon = Icons.AutoMirrored.Filled.ArrowBack to {},
            actionItems = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null
                )
            },
            backgroundColor = Color.Transparent
        )
    }
}