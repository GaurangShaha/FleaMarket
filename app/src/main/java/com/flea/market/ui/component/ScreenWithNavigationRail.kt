package com.flea.market.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.flea.market.ui.main.MainState

@Composable
internal fun ScreenWithNavigationRail(
    uiState: MainState,
    navHost: @Composable () -> Unit,
    snackbarHostState: SnackbarHostState,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .background(MaterialTheme.colors.surface)
            .navigationBarsPadding()
    ) {
        FleaMarketNavigationBar(selectedNavigationItemIndex = uiState.selectedNavigationItemIndex)

        Box(contentAlignment = Alignment.BottomCenter) {
            navHost()
            FleaMarketSnackbarHost(snackbarHostState)
        }
    }
}
