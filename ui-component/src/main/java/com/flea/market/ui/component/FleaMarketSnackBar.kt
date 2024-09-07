package com.flea.market.ui.component

import androidx.annotation.StringRes
import androidx.compose.material.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import com.flea.market.ui.component.SnackbarDelegate.SnackbarType
import com.flea.market.ui.compositionlocal.LocalSnackbarHostState
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine

@Composable
fun FleaMarketSnackBar(snackbarDetails: SnackbarDetails?, onSnackbarResult: (Boolean) -> Unit) {
    val context = LocalContext.current
    val snackbarHostState = LocalSnackbarHostState.current
    LaunchedEffect(snackbarDetails) {
        launch {
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

        // Snackbar is getting displayed on navigating back to screen if LaunchEffect got cancelled
        // before receiving SnackbarResult. This is a fix created to avoid this.
        // Created a suspendCancellableCoroutine which reset the snackbarFlow.
        snackbarDetails?.let {
            launch {
                suspendCancellableCoroutine<Unit> {
                    it.invokeOnCancellation { onSnackbarResult(false) }
                }
            }
        }
    }
}

data class SnackbarDetails(
    @StringRes val message: Int,
    @StringRes val actionLabel: Int? = null,
    val snackbarType: SnackbarType = SnackbarType.DEFAULT,
    val onActionPerformed: (() -> Unit)? = null
)
