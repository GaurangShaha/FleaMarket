package com.flea.common.ui.component

import androidx.compose.material.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import com.flea.common.ui.app.state.rememberSnackBarDelegate
import com.flea.common.ui.component.compositionlocal.LocalSnackbarHostStateProvider
import com.flea.common.ui.slice.SnackBarSlice.SnackBarDetails
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine

@Composable
fun FleaMarketSnackBar(snackBarUiState: SnackBarDetails?, notifySnackbarResult: (Boolean) -> Unit) {
    val snackbarDelegate = rememberSnackBarDelegate()
    val context = LocalContext.current
    val snackbarHostState = LocalSnackbarHostStateProvider.current
    LaunchedEffect(snackBarUiState) {
        launch {
            snackBarUiState?.let { snackBarDetails ->
                notifySnackbarResult(
                    snackbarDelegate.showSnackbar(
                        snackbarHostState,
                        context.getString(snackBarDetails.message),
                        snackBarDetails.actionLabel?.let { context.getString(it) },
                        snackBarDetails.snackbarType
                    ) == SnackbarResult.ActionPerformed
                )
            }
        }

        //Snackbar is getting displayed on navigating back to screen if LaunchEffect got cancelled before receiving SnackbarResult.
        // This is a fix created to avoid this. Created a suspendCancellableCoroutine which reset the snackbarFlow.
        snackBarUiState?.let {
            launch {
                suspendCancellableCoroutine<Unit> {
                    it.invokeOnCancellation { notifySnackbarResult(false) }
                }
            }
        }
    }
}