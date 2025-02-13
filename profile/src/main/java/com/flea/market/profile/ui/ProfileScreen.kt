package com.flea.market.profile.ui

import androidx.annotation.StringRes
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
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.flea.market.ui.component.FMOutlinedButton
import com.flea.market.ui.component.FleaMarketAppBar
import com.flea.market.ui.component.LazyImage
import com.flea.market.ui.component.snackbar.model.SnackbarDetails
import com.flea.market.ui.compositionlocal.LocalSharedUIController
import com.flea.market.ui.compositionlocal.LocalWindowSizeClass
import com.flea.market.ui.helper.findActivity
import com.flea.market.ui.theme.extraColors
import com.flea.market.ui.theme.extraTypography

@Composable
internal fun ProfileScreen(uiState: ProfileUiState) {
    Column {
        FleaMarketAppBar(title = R.string.profile)

        @Suppress("MagicNumber")
        val columnCount =
            if (LocalWindowSizeClass.current.widthSizeClass == WindowWidthSizeClass.Compact) 1 else 3

        val sharedUIController = LocalSharedUIController.current
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(columnCount),
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.surface),
            contentPadding = PaddingValues(bottom = 66.dp)
        ) {
            item {
                UserDetails(uiState)
            }

            items(uiState.menuItem) { (icon, stringRes) ->
                ProfileMenuItem(icon, stringRes) {
                    sharedUIController.showSnackbar(SnackbarDetails(message = R.string.coming_soon))
                }
            }

            item(span = StaggeredGridItemSpan.SingleLane) {
                val activity = findActivity()

                FMOutlinedButton(
                    text = stringResource(id = R.string.logout),
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                ) { activity?.finishAndRemoveTask() }
            }
        }
    }
}

@Composable
private fun UserDetails(uiState: ProfileUiState) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LazyImage(
            url = uiState.profileUrl,
            modifier = Modifier
                .padding(16.dp)
                .size(104.dp)
                .clip(CircleShape)
        )

        Text(text = uiState.name, style = MaterialTheme.typography.h6)

        Text(
            text = uiState.email,
            style = MaterialTheme.extraTypography.body1DarkGray
        )

        Spacer(modifier = Modifier.size(16.dp))
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun ProfileMenuItem(
    icon: ImageVector,
    @StringRes textRes: Int,
    onMenuItemClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .fillMaxWidth(),
        shape = MaterialTheme.shapes.small,
        backgroundColor = MaterialTheme.colors.secondary,
        onClick = onMenuItemClick
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
