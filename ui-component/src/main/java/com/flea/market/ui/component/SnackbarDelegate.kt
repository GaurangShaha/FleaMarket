package com.flea.market.ui.component

import androidx.compose.material.SnackbarHostState
import androidx.compose.material.SnackbarResult

public object SnackbarDelegate {
    public var currentSnackbarType: SnackbarType = SnackbarType.DEFAULT
    public suspend fun showSnackbar(
        snackbarHostState: SnackbarHostState,
        message: String,
        action: String?,
        type: SnackbarType
    ): SnackbarResult {
        currentSnackbarType = type
        return snackbarHostState.showSnackbar(message, action)
    }

    public enum class SnackbarType { DEFAULT, ERROR, SUCCESS }
}
