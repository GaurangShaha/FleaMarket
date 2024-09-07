package com.flea.market.favorite.ui.navigation

import androidx.navigation.NavGraphBuilder
import com.flea.market.favorite.ui.list.navigation.favouriteListScreen

fun NavGraphBuilder.addFavouriteGraph() {
    favouriteListScreen()
}
