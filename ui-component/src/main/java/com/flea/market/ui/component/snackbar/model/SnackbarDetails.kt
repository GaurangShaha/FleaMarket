package com.flea.market.ui.component.snackbar.model

import androidx.annotation.StringRes
import androidx.compose.material.SnackbarDuration
import com.flea.market.ui.component.snackbar.SnackbarDelegate.SnackbarType

public data class SnackbarDetails(
    @StringRes val message: Int,
    val snackbarType: SnackbarType = SnackbarType.DEFAULT,
    val duration: SnackbarDuration = SnackbarDuration.Short
)
