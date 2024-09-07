package com.flea.market.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight

// Set of Material typography styles to start with
internal val Typography = Typography()

@Immutable
public data class ExtraTypography(
    val captionDarkGray: TextStyle = TextStyle.Default,
    val body1Bold: TextStyle = TextStyle.Default,
    val body1DarkGray: TextStyle = TextStyle.Default,
    val h6Bold: TextStyle = TextStyle.Default
)

internal val LocalExtraTypography = staticCompositionLocalOf { ExtraTypography() }

internal val LightExtraTypography = ExtraTypography(
    captionDarkGray = Typography.caption.copy(color = LightExtraColorsPalette.darkGrey),
    body1Bold = Typography.body1.copy(fontWeight = FontWeight.Bold),
    body1DarkGray = Typography.body1.copy(color = LightExtraColorsPalette.darkGrey),
    h6Bold = Typography.h6.copy(fontWeight = FontWeight.Bold)
)

internal val DarkExtraTypography = ExtraTypography(
    captionDarkGray = Typography.caption.copy(color = DarkExtraColorsPalette.darkGrey),
    body1Bold = Typography.body1.copy(fontWeight = FontWeight.Bold),
    body1DarkGray = Typography.body1.copy(color = DarkExtraColorsPalette.darkGrey),
    h6Bold = Typography.h6.copy(fontWeight = FontWeight.Bold)
)

public val MaterialTheme.extraTypography: ExtraTypography
    @Composable @ReadOnlyComposable
    get() = LocalExtraTypography.current
