package com.flea.common.ui.component.preview

import android.content.res.Configuration
import androidx.compose.material.DrawerValue
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Surface
import androidx.compose.material.rememberDrawerState
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Devices.PHONE
import androidx.compose.ui.tooling.preview.Devices.TABLET
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.navigation.compose.rememberNavController
import com.flea.common.ui.app.state.FleaMarketAppState
import com.flea.common.ui.app.state.rememberFleaMarketAppState
import com.flea.common.ui.component.compositionlocal.LocalWindowSizeClass
import com.flea.common.ui.theme.FleaMarketTheme

@Preview(group = "Day", name = "Phone - Portrait", device = PHONE)
@Preview(
    group = "Night",
    name = "Phone - Portrait",
    device = PHONE,
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL
)
@Preview(
    group = "Day",
    name = "Phone - Landscape",
    device = "spec:width=411dp,height=891dp,orientation=landscape"
)
@Preview(
    group = "Night",
    name = "Phone - Landscape",
    device = "spec:width=411dp,height=891dp,orientation=landscape",
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL
)
@Preview(group = "Day", name = "Tablet - Landscape", device = TABLET)
@Preview(
    group = "Night",
    name = "Tablet - Landscape",
    device = TABLET,
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL
)
@Preview(
    group = "Day",
    name = "Tablet - Portrait",
    device = "spec:width=1280dp,height=800dp,orientation=portrait"
)
@Preview(
    group = "Night",
    name = "Tablet - Portrait",
    device = "spec:width=1280dp,height=800dp,orientation=portrait",
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL
)
annotation class FleaMarketPreview

val fleaMarketAppState: FleaMarketAppState
    @Composable get() {
        return rememberFleaMarketAppState(navController = rememberNavController(),
            drawerState = rememberDrawerState(initialValue = DrawerValue.Closed),
            snackbarHostState = remember { SnackbarHostState() })
    }

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun FleaMarketThemePreview(content: @Composable () -> Unit = {}) {
    FleaMarketTheme {
        CompositionLocalProvider(
            LocalWindowSizeClass provides WindowSizeClass.calculateFromSize(DpSize.Zero)
        ) {
            Surface {
                content()
            }
        }
    }
}
