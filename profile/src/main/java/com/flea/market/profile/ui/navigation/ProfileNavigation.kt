package com.flea.market.profile.ui.navigation

import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.flea.market.profile.ui.ProfileScreen
import com.flea.market.profile.ui.ProfileViewModel
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel

public fun NavGraphBuilder.addProfileGraph() {
    profileScreen()
}

@Serializable
public object ProfileDestination

internal fun NavGraphBuilder.profileScreen() {
    composable<ProfileDestination> {
        val viewModel: ProfileViewModel = koinViewModel()
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()
        ProfileScreen(uiState)
    }
}
