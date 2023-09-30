package com.flea.common.ui.app.state

import androidx.compose.material.DrawerState
import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController

@Composable
fun rememberFleaMarketAppState(
    navController: NavHostController, drawerState: DrawerState, snackbarHostState: SnackbarHostState
): FleaMarketAppState {
    return remember(
        navController, drawerState, snackbarHostState
    ) { FleaMarketAppState(navController, drawerState, snackbarHostState) }
}

class FleaMarketAppState(
    val navController: NavHostController,
    val drawerState: DrawerState,
    snackbarHostState: SnackbarHostState
) : SnackbarDelegate by SnackbarDelegateImpl(snackbarHostState)