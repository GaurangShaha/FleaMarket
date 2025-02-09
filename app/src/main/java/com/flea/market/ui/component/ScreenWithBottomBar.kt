package com.flea.market.ui.component

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material.DrawerState
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.flea.market.ui.main.MainState

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
internal fun ScreenWithBottomBar(
    uiState: MainState,
    navHost: @Composable () -> Unit,
    drawerState: DrawerState,
    snackbarHostState: SnackbarHostState,
    modifier: Modifier = Modifier
) {
    val scaffoldState = rememberScaffoldState(
        drawerState = drawerState,
        snackbarHostState = snackbarHostState
    )

    Scaffold(
        modifier = modifier.navigationBarsPadding(),
        scaffoldState = scaffoldState,
        snackbarHost = { FleaMarketSnackbarHost(scaffoldState.snackbarHostState) },
        bottomBar = { FleaMarketNavigationBar(uiState.selectedNavigationItemIndex) }
    ) { _ ->
        navHost()
    }
}
