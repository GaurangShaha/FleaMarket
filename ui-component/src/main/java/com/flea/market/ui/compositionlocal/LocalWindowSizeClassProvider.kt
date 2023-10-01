package com.flea.market.ui.compositionlocal

import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.DpSize

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
val LocalWindowSizeClass =
    compositionLocalOf { WindowSizeClass.calculateFromSize(DpSize.Unspecified) }