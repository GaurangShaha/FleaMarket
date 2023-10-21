package com.flea.market.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.flea.market.ui.preview.FleaMarketPreviews
import com.flea.market.ui.preview.FleaMarketThemePreview

@Composable
fun ScreenWithNavigationRail(
    selectedIndex: Int,
    navHost: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    onSelectNavigationItem: (Int) -> Unit
) {
    Row(
        modifier = modifier
            .background(MaterialTheme.colors.surface)
            .navigationBarsPadding()
    ) {
        FleaMarketNavigationBar(
            selectedNavigationItemIndex = selectedIndex,
            onSelectNavigationItem = onSelectNavigationItem
        )
        Box(contentAlignment = Alignment.BottomCenter) {
            navHost()
            FleaMarketSnackbarHost()
        }
    }
}

@FleaMarketPreviews
@Composable
fun ScreenWithNavigationRailPreview() {
    FleaMarketThemePreview {
        ScreenWithNavigationRail(
            selectedIndex = 0,
            navHost = {}
        ) {}
    }
}
