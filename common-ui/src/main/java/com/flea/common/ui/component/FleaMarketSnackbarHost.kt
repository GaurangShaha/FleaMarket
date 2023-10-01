package com.flea.common.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Snackbar
import androidx.compose.material.SnackbarHost
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.flea.common.ui.component.SnackbarDelegate.SnackbarType
import com.flea.common.ui.component.compositionlocal.LocalSnackbarHostStateProvider
import com.flea.common.ui.theme.extraColors

@Composable
fun FleaMarketSnackbarHost() {
    SnackbarHost(
        modifier = Modifier.padding(16.dp), hostState = LocalSnackbarHostStateProvider.current
    ) { snackbarData ->
        val iconColor: Color
        val icon: ImageVector?
        when (SnackbarDelegate.currentSnackbarType) {
            SnackbarType.ERROR -> {
                iconColor = MaterialTheme.colors.error
                icon = Icons.Default.Close
            }

            SnackbarType.SUCCESS -> {
                iconColor = MaterialTheme.extraColors.successColor
                icon = Icons.Default.Check
            }

            SnackbarType.DEFAULT -> {
                icon = null
                iconColor = MaterialTheme.colors.onSurface
            }
        }

        Snackbar(backgroundColor = MaterialTheme.colors.primary,
            contentColor = MaterialTheme.colors.onPrimary,
            action = snackbarData.actionLabel?.let {
                @Composable {
                    TextButton(colors = ButtonDefaults.textButtonColors(contentColor = MaterialTheme.colors.onPrimary),
                        onClick = { snackbarData.performAction() },
                        content = { Text(it) })
                }
            }) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                icon?.let {
                    Icon(
                        modifier = Modifier.size(24.dp),
                        imageVector = icon,
                        contentDescription = null,
                        tint = iconColor
                    )
                }
                Text(text = snackbarData.message)
            }
        }
    }
}