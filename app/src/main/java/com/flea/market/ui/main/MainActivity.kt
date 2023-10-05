package com.flea.market.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.DrawerValue
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.rememberDrawerState
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.navigation.compose.rememberNavController
import com.flea.market.ui.component.FleaMarketApp
import com.flea.market.ui.compositionlocal.LocalDrawerStateProvider
import com.flea.market.ui.compositionlocal.LocalNavControllerProvider
import com.flea.market.ui.compositionlocal.LocalSnackbarHostStateProvider
import com.flea.market.ui.compositionlocal.LocalWindowSizeClass
import com.flea.market.ui.theme.FleaMarketTheme

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FleaMarketTheme {
                CompositionLocalProvider(LocalWindowSizeClass provides calculateWindowSizeClass(this),
                    LocalNavControllerProvider provides rememberNavController(),
                    LocalDrawerStateProvider provides rememberDrawerState(initialValue = DrawerValue.Closed),
                    LocalSnackbarHostStateProvider provides remember { SnackbarHostState() }) {
                    FleaMarketApp()
                }
            }
        }
    }
}
