package com.flea.market.ui.component

import androidx.compose.material.Surface
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.movableContentOf
import androidx.compose.runtime.remember
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import com.flea.cart.ui.navigation.addCartGraph
import com.flea.common.ui.component.compositionlocal.LocalNavControllerProvider
import com.flea.common.ui.component.compositionlocal.LocalWindowSizeClass
import com.flea.favourite.ui.navigation.addFavouriteGraph
import com.flea.market.ui.main.MainIntent.UpdateSelectedNavigationItemIndex
import com.flea.market.ui.main.MainViewModel
import com.flea.more.ui.navigation.addMoreGraph
import com.flea.product.ui.list.navigation.PRODUCT_LIST_ROUTE
import com.flea.product.ui.navigation.addProductGraph
import org.koin.androidx.compose.koinViewModel

@Composable
internal fun FleaMarketApp() {
    val navHost = remember {
        movableContentOf {
            Surface {
                NavHost(
                    navController = LocalNavControllerProvider.current,
                    startDestination = PRODUCT_LIST_ROUTE
                ) {
                    addProductGraph()
                    addCartGraph()
                    addFavouriteGraph()
                    addMoreGraph()
                }
            }
        }
    }
    val viewModel: MainViewModel = koinViewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val selectedIndex by uiState.selectedNavigationItemIndex.collectAsStateWithLifecycle()
    when (LocalWindowSizeClass.current.widthSizeClass) {
        WindowWidthSizeClass.Compact -> ScreenWithBottomBar(
            navHost = navHost,
            selectedIndex = selectedIndex,
            updateSelectedNavigationItemIndex = {
                viewModel.handleIntent(UpdateSelectedNavigationItemIndex(it))
            })

        else -> ScreenWithNavigationRail(
            navHost = navHost,
            selectedIndex = selectedIndex,
            updateSelectedNavigationItemIndex = {
                viewModel.handleIntent(UpdateSelectedNavigationItemIndex(it))
            })
    }
}