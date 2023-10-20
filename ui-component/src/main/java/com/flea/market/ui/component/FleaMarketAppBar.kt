package com.flea.market.ui.component

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.contentColorFor
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.flea.market.ui.preview.FleaMarketPreviews
import com.flea.market.ui.preview.FleaMarketThemePreview
import com.flea.market.ui.theme.extraColors

@Composable
fun FleaMarketAppBar(
    @StringRes title: Int,
    modifier: Modifier = Modifier,
    navigationIcon: Pair<ImageVector, () -> Unit> = Icons.Default.Menu to {},
    actionItems: @Composable() (RowScope.() -> Unit) = {},
    backgroundColor: Color = MaterialTheme.colors.background,
    contentColor: Color = contentColorFor(backgroundColor),
    elevation: Dp = 0.dp
) {
    TopAppBar(modifier = modifier.statusBarsPadding(), navigationIcon = {
        val (icon, action) = navigationIcon
        IconButton(onClick = action) {
            Icon(
                imageVector = icon,
                modifier = Modifier.padding(18.dp),
                contentDescription = null
            )
        }
    }, actions = actionItems, title = {
        Text(text = stringResource(id = title), textAlign = TextAlign.Center)
    }, backgroundColor = backgroundColor, contentColor = contentColor, elevation = elevation
    )
}

@FleaMarketPreviews
@Composable
fun FleaMarketAppBarPreview() {
    FleaMarketThemePreview {
        FleaMarketAppBar(title = R.string.retry)
    }
}

@FleaMarketPreviews
@Composable
fun FleaMarketAppBarNavigationItemPreview() {
    FleaMarketThemePreview {
        FleaMarketAppBar(title = R.string.retry, navigationIcon = Icons.Default.ArrowBack to {})
    }
}

@FleaMarketPreviews
@Composable
fun FleaMarketAppBarActionIconPreview() {
    FleaMarketThemePreview {
        FleaMarketAppBar(title = R.string.retry,
            navigationIcon = Icons.Default.ArrowBack to {},
            actionItems = {
                Icon(
                    imageVector = Icons.Default.Search, contentDescription = null
                )
            })
    }
}

@FleaMarketPreviews
@Composable
fun FleaMarketAppBarWithScrimPreview() {
    FleaMarketThemePreview {
        FleaMarketAppBar(
            title = R.string.retry,
            modifier = Modifier.background(Brush.verticalGradient(MaterialTheme.extraColors.scrimColor)),
            navigationIcon = Icons.Default.ArrowBack to {},
            actionItems = {
                Icon(
                    imageVector = Icons.Default.Search, contentDescription = null
                )
            },
            backgroundColor = Color.Transparent
        )
    }
}