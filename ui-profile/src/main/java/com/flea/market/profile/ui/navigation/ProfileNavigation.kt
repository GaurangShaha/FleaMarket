package com.flea.market.profile.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.flea.market.profile.ui.ProfileScreen

fun NavGraphBuilder.addProfileGraph() {
    profileScreen()
}

const val PROFILE_ROUTE = "profile"

internal fun NavGraphBuilder.profileScreen() {
    composable(route = PROFILE_ROUTE) {
        ProfileScreen()
    }
}

internal fun NavController.navigateToProfile() {
    navigate(PROFILE_ROUTE)
}