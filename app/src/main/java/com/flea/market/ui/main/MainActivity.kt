package com.flea.market.ui.main

import android.artisan.ui.compositionlocal.LocalNavController
import android.artisan.ui.compositionlocal.LocalSharedUIController
import android.artisan.ui.compositionlocal.LocalWindowSizeClass
import android.artisan.ui.theme.FleaMarketTheme
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import com.flea.market.ui.component.FleaMarketComposeApp
import com.flea.market.ui.main.shared.DefaultSharedUIController
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
internal class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FleaMarketTheme {
                val viewModel: MainViewModel = koinViewModel()
                val uiState by viewModel.uiState.collectAsStateWithLifecycle()

                CompositionLocalProvider(
                    LocalWindowSizeClass provides calculateWindowSizeClass(this),
                    LocalNavController provides rememberNavController(),
                    LocalSharedUIController provides DefaultSharedUIController(viewModel::processIntent)
                ) {
                    FleaMarketComposeApp(uiState)
                }
            }
        }
    }
}
