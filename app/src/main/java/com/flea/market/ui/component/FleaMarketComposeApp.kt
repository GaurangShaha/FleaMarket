package com.flea.market.ui.component

import androidx.compose.material.DrawerValue
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Surface
import androidx.compose.material.rememberDrawerState
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.movableContentOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.NavHost
import com.flea.market.cart.ui.navigation.addCartGraph
import com.flea.market.favorite.ui.navigation.addFavouriteGraph
import com.flea.market.product.ui.list.navigation.ProductListDestination
import com.flea.market.product.ui.navigation.addProductGraph
import com.flea.market.profile.ui.navigation.addProfileGraph
import com.flea.market.ui.component.snackbar.SnackbarDelegate
import com.flea.market.ui.compositionlocal.LocalNavController
import com.flea.market.ui.compositionlocal.LocalSharedUIController
import com.flea.market.ui.compositionlocal.LocalWindowSizeClass
import com.flea.market.ui.main.MainState

@Composable
internal fun FleaMarketComposeApp(uiState: MainState) {
    val navHost = getNavHost()
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val snackbarHostState = remember { SnackbarHostState() }

    uiState.snackbarDetails?.let {
        val message = stringResource(it.message)
        val sharedUIController = LocalSharedUIController.current
        LaunchedEffect(it) {
            SnackbarDelegate.showSnackbar(
                snackbarHostState = snackbarHostState,
                message = message,
                type = it.snackbarType
            )
            sharedUIController.resetSnackbarDetails()
        }
    }

    when (LocalWindowSizeClass.current.widthSizeClass) {
        WindowWidthSizeClass.Compact -> ScreenWithBottomBar(
            uiState = uiState,
            navHost = navHost,
            drawerState = drawerState,
            snackbarHostState = snackbarHostState
        )

        else -> ScreenWithNavigationRail(
            uiState = uiState,
            navHost = navHost,
            snackbarHostState = snackbarHostState
        )
    }
}

@Composable
private fun getNavHost() = remember {
    movableContentOf {
        Surface {
            NavHost(
                navController = LocalNavController.current,
                startDestination = ProductListDestination
            ) {
                addProductGraph()
                addCartGraph()
                addFavouriteGraph()
                addProfileGraph()
            }
        }
    }
}
