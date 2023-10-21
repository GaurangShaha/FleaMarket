package com.flea.market.profile.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridItemSpan
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Build
import androidx.compose.material.icons.outlined.Call
import androidx.compose.material.icons.outlined.List
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.flea.market.ui.component.FMButton
import com.flea.market.ui.component.FleaMarketAppBar
import com.flea.market.ui.component.LazyImage
import com.flea.market.ui.compositionlocal.LocalWindowSizeClassProvider
import com.flea.market.ui.preview.FleaMarketPreviews
import com.flea.market.ui.preview.FleaMarketThemePreview
import com.flea.market.ui.profile.R
import com.flea.market.ui.theme.extraColors
import com.flea.market.ui.theme.extraTypography

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun ProfileScreen() {
    Column {
        FleaMarketAppBar(title = R.string.profile)

        @Suppress("MagicNumber")
        val columnCount =
            if (LocalWindowSizeClassProvider.current.widthSizeClass == WindowWidthSizeClass.Compact) 1 else 3

        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(columnCount),
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.surface),
            contentPadding = PaddingValues(bottom = 66.dp)
        ) {
            item {
                UserDetails()
            }

            item {
                ProfileRowItem(icon = Icons.Outlined.AccountCircle, textRes = R.string.profile)
            }

            item {
                ProfileRowItem(icon = Icons.Outlined.List, textRes = R.string.payment_methods)
            }

            item {
                ProfileRowItem(icon = Icons.Outlined.ShoppingCart, textRes = R.string.order_history)
            }

            item {
                ProfileRowItem(
                    icon = Icons.Outlined.LocationOn,
                    textRes = R.string.delivery_address
                )
            }

            item {
                ProfileRowItem(icon = Icons.Outlined.Call, textRes = R.string.support_center)
            }

            item {
                ProfileRowItem(icon = Icons.Outlined.Build, textRes = R.string.settings)
            }

            item(span = StaggeredGridItemSpan.SingleLane) {
                FMButton(
                    text = stringResource(id = R.string.logout),
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                ) { /*TODO*/ }
            }
        }
    }
}

@Composable
private fun UserDetails() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LazyImage(
            url = "https://miro.medium.com/v2/resize:fit:2400/2*AXmi0bvdz7eiHuUyaD2KBg.jpeg",
            modifier = Modifier
                .padding(16.dp)
                .size(104.dp)
                .clip(CircleShape)
        )

        Text(text = "Gaurang Shaha", style = MaterialTheme.typography.h6)

        Text(
            text = "gaurang.shaha@gmail.com",
            style = MaterialTheme.extraTypography.body1DarkGray
        )

        Spacer(modifier = Modifier.size(16.dp))
    }
}

@Composable
private fun ProfileRowItem(icon: ImageVector, @StringRes textRes: Int) {
    Card(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .fillMaxWidth(),
        shape = MaterialTheme.shapes.small,
        backgroundColor = MaterialTheme.colors.secondary
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = MaterialTheme.extraColors.darkGrey
            )
            Text(
                text = stringResource(id = textRes),
                style = MaterialTheme.typography.body1
            )
        }
    }
}

@Composable
@FleaMarketPreviews
internal fun ProfileScreenPreview() {
    FleaMarketThemePreview {
        ProfileScreen()
    }
}
