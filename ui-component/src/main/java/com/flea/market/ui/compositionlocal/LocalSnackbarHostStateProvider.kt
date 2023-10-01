package com.flea.market.ui.compositionlocal

import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.compositionLocalOf

val LocalSnackbarHostStateProvider: ProvidableCompositionLocal<SnackbarHostState> =
    compositionLocalOf { TODO("SnackbarHostState is not provided") }