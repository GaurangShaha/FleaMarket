package com.flea.common.ui.app.state

import androidx.compose.material.SnackbarHostState
import androidx.compose.material.SnackbarResult
import com.flea.common.ui.app.state.SnackbarDelegate.SnackbarType

interface SnackbarDelegate {
    val snackbarHostState: SnackbarHostState
    var snackbarType: SnackbarType

    suspend fun showSnackbar(message: String, action: String?, type: SnackbarType): SnackbarResult

    enum class SnackbarType { DEFAULT, ERROR, SUCCESS }
}

class SnackbarDelegateImpl(
    override val snackbarHostState: SnackbarHostState,
    override var snackbarType: SnackbarType = SnackbarType.DEFAULT
) : SnackbarDelegate {

    override suspend fun showSnackbar(
        message: String,
        action: String?,
        type: SnackbarType
    ): SnackbarResult {
        snackbarType = type
        return snackbarHostState.showSnackbar(message, action)
    }
}