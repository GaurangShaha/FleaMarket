package com.flea.market.profile.ui.navigation

import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.flea.market.profile.ui.ProfileScreen
import com.flea.market.profile.ui.ProfileViewModel
import org.koin.androidx.compose.koinViewModel

public fun NavGraphBuilder.addProfileGraph() {
    profileScreen()
}

public const val PROFILE_ROUTE: String = "profile"

internal fun NavGraphBuilder.profileScreen() {
    composable(route = PROFILE_ROUTE) {
        val viewModel: ProfileViewModel = koinViewModel()
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()
        ProfileScreen(uiState)
    }
}

internal fun NavController.navigateToProfile() {
    navigate(PROFILE_ROUTE)
}
