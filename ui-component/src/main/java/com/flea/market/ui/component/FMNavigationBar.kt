package com.flea.market.ui.component

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import com.flea.market.ui.compositionlocal.LocalNavController
import com.flea.market.ui.compositionlocal.LocalWindowSizeClass
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.serializer

@Composable
internal fun FleaMarketNavigationBar(
    screens: List<NavigationBarScreen>,
    modifier: Modifier = Modifier
) {
    val navHost = LocalNavController.current
    val navBackStackEntry by navHost.currentBackStackEntryAsState()
    val currentDestinationRoute = navBackStackEntry?.destination?.route
    var selectedNavigationItemIndex by rememberSaveable { mutableIntStateOf(0) }

    if (LocalWindowSizeClass.current.widthSizeClass == WindowWidthSizeClass.Compact) {
        FMBottomNavigation(
            currentDestinationRoute = currentDestinationRoute,
            navigationBarScreens = screens,
            selectedNavigationItemIndex = selectedNavigationItemIndex,
            onNavigationItemSelection = { selectedNavigationItemIndex = it },
            modifier = modifier
        )
    } else {
        FMNavigationRail(
            currentDestinationRoute = currentDestinationRoute,
            navigationBarScreens = screens,
            selectedNavigationItemIndex = selectedNavigationItemIndex,
            onNavigationItemSelection = { selectedNavigationItemIndex = it },
            modifier = modifier
        )
    }
}

public data class NavigationBarScreen(
    val route: Any,
    @StringRes val labelResourceId: Int,
    @DrawableRes val iconResourceId: Int
) {
    @OptIn(InternalSerializationApi::class, ExperimentalSerializationApi::class)
    public fun getSerializedRoute(): String {
        return route::class.serializer().descriptor.serialName
    }
}
