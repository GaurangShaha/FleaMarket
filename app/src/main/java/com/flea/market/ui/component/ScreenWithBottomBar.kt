package com.flea.market.ui.component

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.flea.common.ui.app.state.FleaMarketAppState
import com.flea.common.ui.component.FleaMarketSnackbarHost
import com.flea.common.ui.component.preview.FleaMarketPreview
import com.flea.common.ui.component.preview.FleaMarketThemePreview
import com.flea.common.ui.component.preview.fleaMarketAppState

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ScreenWithBottomBar(
    modifier: Modifier = Modifier,
    appState: FleaMarketAppState,
    navHost: @Composable () -> Unit,
    selectedIndex: Int,
    updateSelectedNavigationItemIndex: (Int) -> Unit
) {
    val scaffoldState = rememberScaffoldState(
        drawerState = appState.drawerState, snackbarHostState = appState.snackbarHostState
    )

    Scaffold(modifier = modifier.navigationBarsPadding(),
        scaffoldState = scaffoldState,
        snackbarHost = {
            FleaMarketSnackbarHost(snackbarHostState = it,
                getSnackbarType = { appState.snackbarType })
        },
        bottomBar = {
            FleaMarketNavigationBar(
                appState = appState,
                selectedNavigationItemIndex = selectedIndex,
                updateSelectedNavigationItemIndex = updateSelectedNavigationItemIndex
            )
        }) { _ ->
        navHost()
    }
}

@FleaMarketPreview
@Composable
fun ScreenWithBottomBarPreview() {
    FleaMarketThemePreview {
        ScreenWithBottomBar(appState = fleaMarketAppState,
            navHost = {},
            selectedIndex = 0,
            updateSelectedNavigationItemIndex = {})
    }
}