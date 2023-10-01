package com.flea.market.ui.compositionlocal

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.compositionLocalOf

val LocalWindowSizeClass: ProvidableCompositionLocal<WindowSizeClass> =
    compositionLocalOf { error("WindowSizeClass is not provided") }