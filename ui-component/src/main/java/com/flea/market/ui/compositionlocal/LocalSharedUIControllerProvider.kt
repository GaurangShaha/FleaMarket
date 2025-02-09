package com.flea.market.ui.compositionlocal

import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.staticCompositionLocalOf
import com.flea.market.ui.component.shared.SharedUIController

public val LocalSharedUIController: ProvidableCompositionLocal<SharedUIController> =
    staticCompositionLocalOf { error("SharedUIController is not provided") }
