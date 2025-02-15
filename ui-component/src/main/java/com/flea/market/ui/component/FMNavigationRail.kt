package com.flea.market.ui.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.Icon
import androidx.compose.material.NavigationRail
import androidx.compose.material.NavigationRailItem
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.flea.market.ui.compositionlocal.LocalNavController
import java.util.Locale

@Composable
internal fun FMNavigationRail(
    currentDestinationRoute: String?,
    navigationBarScreens: List<NavigationBarScreen>,
    selectedNavigationItemIndex: Int,
    onNavigationItemSelection: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val navHost = LocalNavController.current
    NavigationRail(modifier = modifier.padding(end = 2.dp)) {
        navigationBarScreens.forEachIndexed { index, screen ->
            if (currentDestinationRoute?.contains(screen.getSerializedRoute()) == true &&
                index != selectedNavigationItemIndex
            ) {
                onNavigationItemSelection(index)
            }
            if (index == 0) {
                Spacer(modifier = Modifier.statusBarsPadding())
            }
            NavigationRailItem(selected = selectedNavigationItemIndex == index, onClick = {
                navigateToDestinations(
                    index = index,
                    navigationBarScreen = screen,
                    navController = navHost,
                    currentDestinationRoute = currentDestinationRoute,
                    onNavigationItemSelected = onNavigationItemSelection
                )
            }, alwaysShowLabel = false, icon = {
                Icon(
                    modifier = Modifier.size(24.dp),
                    painter = painterResource(id = screen.iconResourceId),
                    contentDescription = null
                )
            }, label = {
                Text(
                    text = stringResource(id = screen.labelResourceId).uppercase(
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
