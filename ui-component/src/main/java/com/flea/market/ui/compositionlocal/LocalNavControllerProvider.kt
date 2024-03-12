package com.flea.market.ui.compositionlocal

import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.navigation.NavHostController

val LocalNavController: ProvidableCompositionLocal<NavHostController> =
    staticCompositionLocalOf { error("NavHostController is not provided") }
