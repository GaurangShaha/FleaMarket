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
import com.flea.common.ui.app.state.rememberFleaMarketAppState
import com.flea.common.ui.component.compositionlocal.LocalWindowSizeClass
import com.flea.common.ui.theme.FleaMarketTheme
import com.flea.market.ui.component.FleaMarketApp

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FleaMarketTheme {
                CompositionLocalProvider(LocalWindowSizeClass provides calculateWindowSizeClass(this)) {
                    FleaMarketApp(
                        rememberFleaMarketAppState(navController = rememberNavController(),
                            drawerState = rememberDrawerState(initialValue = DrawerValue.Open),
                            snackbarHostState = remember { SnackbarHostState() })
                    )
                }
            }
        }
    }
}
