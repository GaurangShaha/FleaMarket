package com.flea.market.ui.component

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.flea.common.ui.component.FleaMarketSnackbarHost
import com.flea.common.ui.component.compositionlocal.LocalDrawerStateProvider
import com.flea.common.ui.component.compositionlocal.LocalSnackbarHostStateProvider
import com.flea.common.ui.component.preview.FleaMarketPreview
import com.flea.common.ui.component.preview.FleaMarketThemePreview

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ScreenWithBottomBar(
    modifier: Modifier = Modifier,
    navHost: @Composable () -> Unit,
    selectedIndex: Int,
    updateSelectedNavigationItemIndex: (Int) -> Unit
) {
    val scaffoldState = rememberScaffoldState(
        drawerState = LocalDrawerStateProvider.current,
        snackbarHostState = LocalSnackbarHostStateProvider.current
    )

    Scaffold(modifier = modifier.navigationBarsPadding(),
        scaffoldState = scaffoldState,
        snackbarHost = { FleaMarketSnackbarHost() },
        bottomBar = {
            FleaMarketNavigationBar(
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
        ScreenWithBottomBar(navHost = {}, selectedIndex = 0, updateSelectedNavigationItemIndex = {})
    }
}