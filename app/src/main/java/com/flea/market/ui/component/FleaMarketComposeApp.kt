package com.flea.market.ui.component

import android.artisan.ui.component.NavigationBarScreen
import android.artisan.ui.component.ScreenWithBottomBar
import android.artisan.ui.component.ScreenWithNavigationRail
import android.artisan.ui.component.snackbar.SnackbarDelegate
import android.artisan.ui.compositionlocal.LocalNavController
import android.artisan.ui.compositionlocal.LocalSharedUIController
import android.artisan.ui.compositionlocal.LocalWindowSizeClass
import androidx.compose.material.DrawerValue
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.rememberDrawerState
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.movableContentOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.NavHost
import com.flea.market.R
import com.flea.market.cart.ui.details.navigation.CartDetailsDestination
import com.flea.market.cart.ui.navigation.addCartGraph
import com.flea.market.favorite.ui.list.navigation.FavouriteListDestination
import com.flea.market.favorite.ui.navigation.addFavouriteGraph
import com.flea.market.product.ui.list.navigation.ProductListDestination
import com.flea.market.product.ui.navigation.addProductGraph
import com.flea.market.profile.ui.navigation.ProfileDestination
import com.flea.market.profile.ui.navigation.addProfileGraph
import com.flea.market.ui.main.MainState
import okhttp3.internal.immutableListOf

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
            navHost = navHost,
            drawerState = drawerState,
            snackbarHostState = snackbarHostState,
            screens = navigationScreens
        )

        else -> ScreenWithNavigationRail(
            navHost = navHost,
            snackbarHostState = snackbarHostState,
            screens = navigationScreens
        )
    }
}

private val navigationScreens = immutableListOf(
    NavigationBarScreen(
        route = ProductListDestination,
        labelResourceId = R.string.home,
        iconResourceId = R.drawable.ic_home
    ),
    NavigationBarScreen(
        route = CartDetailsDestination,
        labelResourceId = R.string.cart,
        iconResourceId = android.artisan.ui.component.R.drawable.ic_cart
    ),
    NavigationBarScreen(
        route = FavouriteListDestination,
        labelResourceId = R.string.favourites,
        iconResourceId = R.drawable.ic_favourite
    ),
    NavigationBarScreen(
        route = ProfileDestination,
        labelResourceId = com.flea.market.profile.ui.R.string.profile,
        iconResourceId = R.drawable.ic_more
    )
)

@Composable
private fun getNavHost() = remember {
    movableContentOf {
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
