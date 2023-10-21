package com.flea.market.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

internal val Black200 = Color(0xFF1D1C1C)
internal val White200 = Color(0xFFFDFDFD)
internal val Grey = Color(0xFFEBEAEF)
internal val Peach = Color(0xFFEE3140)

@Immutable
data class ExtraColorsPalette(
    val darkGrey: Color = Color.Unspecified,
    val scrimColor: List<Color> = listOf(Color.Unspecified, Color.Unspecified),
    val onScrimColor: Color = Color.Unspecified,
    val deselectedPageIndicator: Color = Color.Unspecified,
    val dividerColor: Color = Color.Unspecified,
    val successColor: Color = Color.Unspecified,
    val inActiveStarColor: Color = Color.Unspecified,
    val shimmerColors: List<Color> = listOf(
        Color.Unspecified,
        Color.Unspecified,
        Color.Unspecified,
    ),
    val selectedNavigationItemColor: Color = Color.Unspecified
)

internal val LocalExtraColorsPalette = staticCompositionLocalOf { ExtraColorsPalette() }

internal val LightExtraColorsPalette = ExtraColorsPalette(
    darkGrey = Color(color = 0xFF706E75),
    scrimColor = listOf(Color(color = 0x66000000), Color.Transparent),
    onScrimColor = Color.White,
    deselectedPageIndicator = Color(color = 0x99716F75),
    dividerColor = Color.LightGray,
    successColor = Color.Green,
    inActiveStarColor = Color.LightGray,
    shimmerColors = listOf(
        Color(0xFFD6D6D6),
        Color(0xFFB8B8B8),
        Color(0xFFD6D6D6),
    ),
    selectedNavigationItemColor = Color(0xFFEB914C)
)

internal val DarkExtraColorsPalette = ExtraColorsPalette(
    darkGrey = Color(color = 0xFFC7C7CC),
    scrimColor = listOf(Color(color = 0x66000000), Color.Transparent),
    onScrimColor = Color.White,
    deselectedPageIndicator = Color(color = 0x99716F75),
    dividerColor = Color.LightGray,
    successColor = Color(0xFF00E600),
    inActiveStarColor = Color(0xB3CCCCCC),
    shimmerColors = listOf(
        Color(0XFF3A3A3A),
        Color(0XFF4A4A4A),
        Color(0XFF3A3A3A),
    ),
    selectedNavigationItemColor = Color(0xFFFF9800)
)

val MaterialTheme.extraColors: ExtraColorsPalette
    @Composable @ReadOnlyComposable
    get() = LocalExtraColorsPalette.current
