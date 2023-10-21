package com.flea.market.ui.component

import androidx.compose.material.SnackbarHostState
import androidx.compose.material.SnackbarResult

object SnackbarDelegate {
    var currentSnackbarType: SnackbarType = SnackbarType.DEFAULT
    suspend fun showSnackbar(
        snackbarHostState: SnackbarHostState,
        message: String,
        action: String?,
        type: SnackbarType
    ): SnackbarResult {
        currentSnackbarType = type
        return snackbarHostState.showSnackbar(message, action)
    }

    enum class SnackbarType { DEFAULT, ERROR, SUCCESS }
}
