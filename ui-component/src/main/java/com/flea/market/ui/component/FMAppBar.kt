package com.flea.market.ui.component

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
public fun FleaMarketAppBar(
    @StringRes title: Int,
    navigationIcon: Pair<ImageVector, () -> Unit>,
    modifier: Modifier = Modifier,
    actionItems: @Composable (RowScope.() -> Unit) = {},
    backgroundColor: Color = MaterialTheme.colors.background,
    contentColor: Color = contentColorFor(backgroundColor),
    elevation: Dp = 0.dp
) {
    TopAppBar(
        modifier = modifier.statusBarsPadding(),
        navigationIcon = {
            val (icon, action) = navigationIcon
            IconButton(onClick = action) {
                Icon(
                    imageVector = icon,
                    modifier = Modifier.padding(18.dp),
                    contentDescription = null
                )
            }
        },
        actions = actionItems,
        title = {
            Text(text = stringResource(id = title), textAlign = TextAlign.Center)
        },
        backgroundColor = backgroundColor,
        contentColor = contentColor,
        elevation = elevation
    )
}
