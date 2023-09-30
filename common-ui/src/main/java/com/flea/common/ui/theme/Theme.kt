package com.flea.common.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val LightColorPalette = lightColors(
    primary = Black200,
    primaryVariant = Color.Black,
    secondary = White200,
    secondaryVariant = Color.White,
    background = Grey,
    surface = Grey,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    error = Peach,
    onError = Color.White
)

private val DarkColorPalette = darkColors(
    primary = White200,
    primaryVariant = Color.White,
    secondary = Black200,
    secondaryVariant = Color.Black,
    background = Black200,
    surface = Black200,
    onPrimary = Color.Black,
    onSecondary = Color.White,
    onBackground = Color.White,
    onSurface = Color.White,
    error = Peach,
    onError = Color.White
)

@Composable
fun FleaMarketTheme(
    useDarkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit
) {
    val extraColorsPalette: ExtraColorsPalette
    val extraTypography: ExtraTypography
    val colorPalette: Colors
    if (useDarkTheme) {
        extraColorsPalette = DarkExtraColorsPalette
        extraTypography = DarkExtraTypography
        colorPalette = DarkColorPalette
    } else {
        extraColorsPalette = LightExtraColorsPalette
        extraTypography = LightExtraTypography
        colorPalette = LightColorPalette
    }

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            WindowCompat.setDecorFitsSystemWindows(window, false)
            window.statusBarColor = Color.Transparent.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars =
                !useDarkTheme
        }
    }

    CompositionLocalProvider(
        LocalExtraColorsPalette provides extraColorsPalette,
        LocalExtraTypography provides extraTypography,
        LocalExtraShape provides ExtraShapes
    ) {
        MaterialTheme(
            colors = colorPalette, typography = Typography, shapes = Shapes, content = content
        )
    }
}

@Composable
fun DarkStatusBarDisposableEffect(useDarkTheme: Boolean = isSystemInDarkTheme()) {
    val view = LocalView.current
    DisposableEffect(Unit) {
        val window = (view.context as Activity).window
        WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = false

        onDispose {
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars =
                !useDarkTheme
        }
    }
}