package com.flea.market.ui.component

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navOptions
import com.flea.cart.ui.details.navigation.CART_DETAILS_ROUTE
import com.flea.common.ui.component.compositionlocal.LocalNavControllerProvider
import com.flea.common.ui.component.compositionlocal.LocalWindowSizeClass
import com.flea.common.ui.component.preview.FleaMarketPreview
import com.flea.common.ui.component.preview.FleaMarketThemePreview
import com.flea.common.ui.theme.extraColors
import com.flea.common.ui.theme.extraShape
import com.flea.favourite.ui.list.navigation.FAVOURITE_LIST_ROUTE
import com.flea.market.R
import com.flea.more.ui.dummy.navigation.DUMMY_MORE_ROUTE
import com.flea.product.ui.list.navigation.PRODUCT_LIST_ROUTE
import java.util.*

@OptIn(ExperimentalAnimationApi::class)
@Composable
internal fun FleaMarketNavigationBar(
    modifier: Modifier = Modifier,
    selectedNavigationItemIndex: Int,
    updateSelectedNavigationItemIndex: (Int) -> Unit
) {
    val navController = LocalNavControllerProvider.current
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestinationRoute = navBackStackEntry?.destination?.route

    val destinations = BottomNavigationScreens.values()

    fun navigateToDestinations(
        index: Int, bottomNavigationScreens: BottomNavigationScreens
    ) {
        if (currentDestinationRoute != bottomNavigationScreens.route) {
            updateSelectedNavigationItemIndex(index)
            val topLevelNavOptions = navOptions {
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }

            navController.navigate(
                route = bottomNavigationScreens.route, navOptions = topLevelNavOptions
            )
        }
    }

    if (LocalWindowSizeClass.current.widthSizeClass == WindowWidthSizeClass.Compact) {
        AnimatedVisibility(
            visible = destinations.any { currentDestinationRoute?.equals(it.route) ?: true },
            enter = scaleIn(),
            exit = scaleOut()
        ) {
            BottomNavigation(
                modifier = modifier
                    .padding(16.dp)
                    .clip(MaterialTheme.extraShape.capsuleShape)
            ) {
                destinations.forEachIndexed { index, it ->
                    if (currentDestinationRoute == it.route && index != selectedNavigationItemIndex) {
                        updateSelectedNavigationItemIndex(index)
                    }

                    BottomNavigationItem(selected = selectedNavigationItemIndex == index, icon = {
                        if (selectedNavigationItemIndex != index) {
                            Icon(
                                modifier = Modifier.size(24.dp),
                                painter = painterResource(id = it.iconResourceId),
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
                    }, label = {
                        Text(
                            text = stringResource(id = it.labelResourceId).uppercase(Locale.getDefault()),
                            overflow = TextOverflow.Visible,
                            softWrap = false,
                            maxLines = 1
                        )
                    }, onClick = {
                        navigateToDestinations(index, it)
                    }, alwaysShowLabel = false
                    )
                }
            }
        }
    } else {
        NavigationRail(modifier = modifier.padding(end = 2.dp)) {
            destinations.forEachIndexed { index, destination ->
                if (currentDestinationRoute == destination.route && index != selectedNavigationItemIndex) {
                    updateSelectedNavigationItemIndex(index)
                }
                if (index == 0) {
                    Spacer(modifier = Modifier.statusBarsPadding())
                }
                NavigationRailItem(selected = selectedNavigationItemIndex == index,
                    onClick = { navigateToDestinations(index, destination) },
                    alwaysShowLabel = false,
                    icon = {
                        Icon(
                            modifier = Modifier.size(24.dp),
                            painter = painterResource(id = destination.iconResourceId),
                            contentDescription = null
                        )
                    },
                    label = {
                        Text(
                            text = stringResource(id = destination.labelResourceId).uppercase(
                                Locale.getDefault()
                            ), overflow = TextOverflow.Visible, softWrap = false, maxLines = 1
                        )
                    })
            }
        }
    }
}


enum class BottomNavigationScreens(
    val route: String, @StringRes val labelResourceId: Int, @DrawableRes val iconResourceId: Int
) {
    HOME(
        route = PRODUCT_LIST_ROUTE,
        labelResourceId = R.string.home,
        iconResourceId = R.drawable.ic_home
    ),

    CART(
        route = CART_DETAILS_ROUTE,
        labelResourceId = R.string.cart,
        iconResourceId = com.flea.common.ui.R.drawable.ic_cart
    ),

    FAVOURITE(
        route = FAVOURITE_LIST_ROUTE,
        labelResourceId = R.string.favourites,
        iconResourceId = R.drawable.ic_favourite
    ),

    MORE(
        route = DUMMY_MORE_ROUTE,
        labelResourceId = R.string.more,
        iconResourceId = R.drawable.ic_more
    );
}

@Composable
@FleaMarketPreview
private fun FleaMarketNavigationBarPreview() {
    FleaMarketThemePreview {
        FleaMarketNavigationBar(
            selectedNavigationItemIndex = 0,
            updateSelectedNavigationItemIndex = {})
    }
}