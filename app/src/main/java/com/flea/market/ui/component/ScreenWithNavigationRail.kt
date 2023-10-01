package com.flea.market.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.flea.common.ui.component.FleaMarketSnackbarHost
import com.flea.common.ui.component.preview.FleaMarketPreview
import com.flea.common.ui.component.preview.FleaMarketThemePreview

@Composable
fun ScreenWithNavigationRail(
    modifier: Modifier = Modifier,
    navHost: @Composable () -> Unit,
    selectedIndex: Int,
    updateSelectedNavigationItemIndex: (Int) -> Unit
) {
    Row(
        modifier = modifier
            .background(MaterialTheme.colors.surface)
            .navigationBarsPadding()
    ) {
        FleaMarketNavigationBar(
            selectedNavigationItemIndex = selectedIndex,
            updateSelectedNavigationItemIndex = updateSelectedNavigationItemIndex
        )
        Box(contentAlignment = Alignment.BottomCenter) {
            navHost()
            FleaMarketSnackbarHost()
        }
    }
}


@FleaMarketPreview
@Composable
fun ScreenWithNavigationRailPreview() {
    FleaMarketThemePreview {
        ScreenWithNavigationRail(navHost = {},
            selectedIndex = 0,
            updateSelectedNavigationItemIndex = {})
    }
}