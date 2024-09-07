package com.flea.market.favorite.ui.navigation

import androidx.navigation.NavGraphBuilder
import com.flea.market.favorite.ui.list.navigation.favouriteListScreen

public fun NavGraphBuilder.addFavouriteGraph() {
    favouriteListScreen()
}
