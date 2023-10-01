package com.flea.market.ui.compositionlocal

import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.compositionLocalOf
import androidx.navigation.NavHostController

val LocalNavControllerProvider: ProvidableCompositionLocal<NavHostController> =
    compositionLocalOf { error("NavHostController is not provided") }