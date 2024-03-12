package com.flea.market.ui.compositionlocal

import androidx.compose.material.DrawerState
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.staticCompositionLocalOf

val LocalDrawerState: ProvidableCompositionLocal<DrawerState> =
    staticCompositionLocalOf { error("DrawerState is not provided") }
