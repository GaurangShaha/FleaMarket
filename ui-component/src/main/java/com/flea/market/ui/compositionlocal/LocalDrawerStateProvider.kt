package com.flea.market.ui.compositionlocal

import androidx.compose.material.DrawerState
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.staticCompositionLocalOf

val LocalDrawerStateProvider: ProvidableCompositionLocal<DrawerState> =
    staticCompositionLocalOf { error("DrawerState is not provided") }
