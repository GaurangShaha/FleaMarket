package com.flea.market.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.flea.market.ui.preview.FleaMarketPreview
import com.flea.market.ui.preview.FleaMarketThemePreview
import com.flea.market.ui.theme.extraColors

@Composable
fun PageIndicator(modifier: Modifier = Modifier, totalPages: Int, currentPage: Int) {
    if (totalPages == 1) return
    Row(modifier = modifier, horizontalArrangement = Arrangement.Center) {
        repeat(totalPages) {
            val color = if (currentPage == it) {
                MaterialTheme.colors.primary
            } else {
                MaterialTheme.extraColors.deselectedPageIndicator
            }
            Box(
                Modifier
                    .size(24.dp)
                    .padding(6.dp)
                    .clip(CircleShape)
                    .border(
                        width = 1.dp, color = MaterialTheme.colors.secondary, shape = CircleShape
                    )
                    .background(color)
            )
        }
    }
}

@FleaMarketPreview
@Composable
fun PageIndicatorPreview() {
    FleaMarketThemePreview {
        PageIndicator(totalPages = 5, currentPage = 1)
    }
}