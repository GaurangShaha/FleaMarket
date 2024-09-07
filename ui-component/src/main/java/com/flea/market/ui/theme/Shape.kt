package com.flea.market.ui.theme

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Shapes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

internal val Shapes = Shapes(
    small = RoundedCornerShape(8.dp),
    medium = RoundedCornerShape(16.dp),
    large = RoundedCornerShape(22.dp)
)

@Immutable
public data class ExtraShape(
    val xlargeShape: Shape = GenericShape { _, _ -> },
    val capsuleShape: Shape = GenericShape { _, _ -> }
)

internal val LocalExtraShape = staticCompositionLocalOf { ExtraShape() }

internal val ExtraShapes = ExtraShape(
    xlargeShape = RoundedCornerShape(32.dp),
    capsuleShape = CircleShape
)

public val MaterialTheme.extraShape: ExtraShape
    @Composable @ReadOnlyComposable
    get() = LocalExtraShape.current
