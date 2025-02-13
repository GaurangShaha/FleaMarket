package com.flea.market.ui.component

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.NavigationRail
import androidx.compose.material.NavigationRailItem
import androidx.compose.material.Text
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navOptions
import com.flea.market.R
import com.flea.market.cart.ui.details.navigation.CartDetailsDestination
import com.flea.market.favorite.ui.list.navigation.FavouriteListDestination
import com.flea.market.product.ui.list.navigation.ProductListDestination
import com.flea.market.profile.ui.navigation.ProfileDestination
import com.flea.market.ui.component.shared.SharedUIController
import com.flea.market.ui.compositionlocal.LocalNavController
import com.flea.market.ui.compositionlocal.LocalSharedUIController
import com.flea.market.ui.compositionlocal.LocalWindowSizeClass
import com.flea.market.ui.theme.extraColors
import com.flea.market.ui.theme.extraShape
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.serializer
import java.util.Locale

@Composable
internal fun FleaMarketNavigationBar(
    selectedNavigationItemIndex: Int,
    modifier: Modifier = Modifier
) {
    val navHost = LocalNavController.current
    val navBackStackEntry by navHost.currentBackStackEntryAsState()
    val currentDestinationRoute = navBackStackEntry?.destination?.route

    if (LocalWindowSizeClass.current.widthSizeClass == WindowWidthSizeClass.Compact) {
        FMBottomNavigation(
            currentDestinationRoute,
            selectedNavigationItemIndex,
            modifier
        )
    } else {
        FMNavigationRail(
            currentDestinationRoute,
            selectedNavigationItemIndex,
            modifier
        )
    }
}

@Composable
private fun FMNavigationRail(
    currentDestinationRoute: String?,
    selectedNavigationItemIndex: Int,
    modifier: Modifier = Modifier
) {
    val navHost = LocalNavController.current
    val sharedUIController = LocalSharedUIController.current
    NavigationRail(modifier = modifier.padding(end = 2.dp)) {
        BottomNavigationScreens.entries.forEachIndexed { index, destination ->
            if (currentDestinationRoute?.contains(destination.getSerializedRoute()) == true  && index != selectedNavigationItemIndex) {
                sharedUIController.updateSelectedNavigationItemIndex(index)
            }
            if (index == 0) {
                Spacer(modifier = Modifier.statusBarsPadding())
            }
            NavigationRailItem(selected = selectedNavigationItemIndex == index, onClick = {
                navigateToDestinations(
                    index = index,
                    bottomNavigationScreens = destination,
                    navController = navHost,
                    currentDestinationRoute = currentDestinationRoute,
                    sharedUIController = sharedUIController
                )
            }, alwaysShowLabel = false, icon = {
                Icon(
                    modifier = Modifier.size(24.dp),
                    painter = painterResource(id = destination.iconResourceId),
                    contentDescription = null
                )
            }, label = {
                Text(
                    text = stringResource(id = destination.labelResourceId).uppercase(
                        Locale.getDefault()
                    ),
                    overflow = TextOverflow.Visible,
                    softWrap = false,
                    maxLines = 1
                )
            })
        }
    }
}

@Composable
private fun FMBottomNavigation(
    currentDestinationRoute: String?,
    selectedNavigationItemIndex: Int,
    modifier: Modifier = Modifier
) {
    val navHost = LocalNavController.current
    val sharedUIController = LocalSharedUIController.current
    AnimatedVisibility(
        visible = isBottomBarVisible(currentDestinationRoute),
        enter = scaleIn(),
        exit = scaleOut()
    ) {
        BottomNavigation(
            modifier = modifier
                .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                .clip(MaterialTheme.extraShape.capsuleShape)
        ) {
            BottomNavigationScreens.entries.forEachIndexed { index, screen ->
                if (currentDestinationRoute?.contains(screen.getSerializedRoute()) == true && index != selectedNavigationItemIndex) {
                    sharedUIController.updateSelectedNavigationItemIndex(index)
                }
                BottomNavigationItem(
                    selected = selectedNavigationItemIndex == index,
                    icon = {
                        if (selectedNavigationItemIndex != index) {
                            Icon(
                                modifier = Modifier.size(24.dp),
                                painter = painterResource(id = screen.iconResourceId),
                                contentDescription = null
                            )
                        } else {
                            Box(
                                modifier = Modifier
                                    .size(24.dp)
                                    .padding(8.dp)
                                    .clip(CircleShape)
                                    .background(MaterialTheme.extraColors.selectedNavigationItemColor)
                            )
                        }
                    },
                    label = {
                        Text(
                            text = stringResource(id = screen.labelResourceId).uppercase(Locale.getDefault()),
                            overflow = TextOverflow.Visible,
                            softWrap = false,
                            maxLines = 1
                        )
                    },
                    onClick = {
                        navigateToDestinations(
                            index = index,
                            bottomNavigationScreens = screen,
                            navController = navHost,
                            currentDestinationRoute = currentDestinationRoute,
                            sharedUIController = sharedUIController
                        )
                    },
                    alwaysShowLabel = false
                )
            }
        }
    }
}

private fun isBottomBarVisible(currentDestinationRoute: String?) = BottomNavigationScreens.entries
    .any { currentDestinationRoute?.contains(it.getSerializedRoute()) == true }

private fun navigateToDestinations(
    index: Int,
    bottomNavigationScreens: BottomNavigationScreens,
    navController: NavController,
    currentDestinationRoute: String?,
    sharedUIController: SharedUIController
) {
    if (currentDestinationRoute != bottomNavigationScreens.route) {
        sharedUIController.updateSelectedNavigationItemIndex(index)
        val topLevelNavOptions = navOptions {
            popUpTo(navController.graph.findStartDestination().id)
            launchSingleTop = true
        }

        navController.navigate(
            route = bottomNavigationScreens.route,
            navOptions = topLevelNavOptions
        )
    }
}

private enum class BottomNavigationScreens(
    val route: Any,
    @StringRes val labelResourceId: Int,
    @DrawableRes val iconResourceId: Int
) {
    HOME(
        route = ProductListDestination,
        labelResourceId = R.string.home,
        iconResourceId = R.drawable.ic_home
    ),

    CART(
        route = CartDetailsDestination,
        labelResourceId = R.string.cart,
        iconResourceId = com.flea.market.ui.component.R.drawable.ic_cart
    ),

    FAVOURITE(
        route = FavouriteListDestination,
        labelResourceId = R.string.favourites,
        iconResourceId = R.drawable.ic_favourite
    ),

    PROFILE(
        route = ProfileDestination,
        labelResourceId = com.flea.market.profile.ui.R.string.profile,
        iconResourceId = R.drawable.ic_more
    );

    @OptIn(InternalSerializationApi::class, ExperimentalSerializationApi::class)
    fun getSerializedRoute(): String {
        return route::class.serializer().descriptor.serialName
    }
}
