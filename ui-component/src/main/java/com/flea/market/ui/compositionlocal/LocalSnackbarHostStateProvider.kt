package com.flea.market.ui.compositionlocal

import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.staticCompositionLocalOf

val LocalSnackbarHostStateProvider: ProvidableCompositionLocal<SnackbarHostState> =
    staticCompositionLocalOf { error("SnackbarHostState is not provided") }