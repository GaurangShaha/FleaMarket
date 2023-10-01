package com.flea.market.ui.compositionlocal

import androidx.compose.material.DrawerState
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.compositionLocalOf

val LocalDrawerStateProvider: ProvidableCompositionLocal<DrawerState> =
    compositionLocalOf { error("DrawerState is not provided") }