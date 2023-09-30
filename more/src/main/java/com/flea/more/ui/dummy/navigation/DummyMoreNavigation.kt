package com.flea.more.ui.dummy.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.flea.more.ui.dummy.DummyMore

const val DUMMY_MORE_ROUTE = "more_dummy"

internal fun NavGraphBuilder.dummyFavouriteScreen() {
    composable(route = DUMMY_MORE_ROUTE) {
        DummyMore()
    }
}

internal fun NavController.navigateToMore() {
    navigate(DUMMY_MORE_ROUTE)
}