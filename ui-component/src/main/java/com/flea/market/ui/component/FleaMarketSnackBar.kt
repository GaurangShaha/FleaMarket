package com.flea.market.ui.component

import androidx.annotation.StringRes
import androidx.compose.material.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import com.flea.market.ui.component.SnackbarDelegate.SnackbarType
import com.flea.market.ui.compositionlocal.LocalSnackbarHostState
import kotlinx.coroutines.launch

@Composable
public fun FleaMarketSnackBar(snackbarDetails: SnackbarDetails?, onSnackbarResult: (Boolean) -> Unit) {
    val context = LocalContext.current
    val snackbarHostState = LocalSnackbarHostState.current
    val coroutineScope = rememberCoroutineScope()

    DisposableEffect(snackbarDetails) {
        coroutineScope.launch {
            snackbarDetails?.let { snackBarDetails ->
                onSnackbarResult(
                    SnackbarDelegate.showSnackbar(
                        snackbarHostState,
                        context.getString(snackBarDetails.message),
                        snackBarDetails.actionLabel?.let { context.getString(it) },
                        snackBarDetails.snackbarType
                    ) == SnackbarResult.ActionPerformed
                )
            }
        }

        onDispose {
            onSnackbarResult(false)
        }
    }
}

public data class SnackbarDetails(
    @StringRes val message: Int,
    @StringRes val actionLabel: Int? = null,
    val snackbarType: SnackbarType = SnackbarType.DEFAULT,
    val onActionPerformed: (() -> Unit)? = null
)
