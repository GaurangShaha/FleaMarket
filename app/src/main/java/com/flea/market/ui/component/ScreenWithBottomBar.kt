package com.flea.market.ui.component

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.flea.market.ui.compositionlocal.LocalDrawerState
import com.flea.market.ui.compositionlocal.LocalSnackbarHostState

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
internal fun ScreenWithBottomBar(
    selectedIndex: Int,
    navHost: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    onSelectNavigationItem: (Int) -> Unit
) {
    val scaffoldState = rememberScaffoldState(
        drawerState = LocalDrawerState.current,
        snackbarHostState = LocalSnackbarHostState.current
    )

    Scaffold(
        modifier = modifier.navigationBarsPadding(),
        scaffoldState = scaffoldState,
        snackbarHost = { FleaMarketSnackbarHost() },
        bottomBar = {
            FleaMarketNavigationBar(
                selectedNavigationItemIndex = selectedIndex,
                onSelectNavigationItem = onSelectNavigationItem
            )
        }
    ) { _ ->
        navHost()
    }
}
