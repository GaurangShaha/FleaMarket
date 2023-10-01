package com.flea.favourite.ui.navigation

import androidx.navigation.NavGraphBuilder
import com.flea.favourite.ui.list.navigation.favouriteListScreen

fun NavGraphBuilder.addFavouriteGraph() {
    favouriteListScreen()
}