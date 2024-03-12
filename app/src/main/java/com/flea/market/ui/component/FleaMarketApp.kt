package com.flea.market.ui.component

import androidx.compose.material.Surface
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.movableContentOf
import androidx.compose.runtime.remember
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import com.flea.market.cart.ui.navigation.addCartGraph
import com.flea.market.favorite.ui.navigation.addFavouriteGraph
import com.flea.market.product.ui.list.navigation.PRODUCT_LIST_ROUTE
import com.flea.market.product.ui.navigation.addProductGraph
import com.flea.market.profile.ui.navigation.addProfileGraph
import com.flea.market.ui.compositionlocal.LocalNavController
import com.flea.market.ui.compositionlocal.LocalWindowSizeClass
import com.flea.market.ui.main.MainIntent.UpdateSelectedNavigationItemIndex
import com.flea.market.ui.main.MainViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
internal fun FleaMarketApp() {
    val navHost = remember {
        movableContentOf {
            Surface {
                NavHost(
                    navController = LocalNavController.current,
                    startDestination = PRODUCT_LIST_ROUTE
                ) {
                    addProductGraph()
                    addCartGraph()
                    addFavouriteGraph()
                    addProfileGraph()
                }
            }
        }
    }
    val viewModel: MainViewModel = koinViewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    when (LocalWindowSizeClass.current.widthSizeClass) {
        WindowWidthSizeClass.Compact -> ScreenWithBottomBar(
            selectedIndex = uiState.selectedNavigationItemIndex,
            navHost = navHost
        ) {
            viewModel.onHandleIntent(UpdateSelectedNavigationItemIndex(it))
        }

        else -> ScreenWithNavigationRail(
            selectedIndex = uiState.selectedNavigationItemIndex,
            navHost = navHost
        ) {
            viewModel.onHandleIntent(UpdateSelectedNavigationItemIndex(it))
        }
    }
}
