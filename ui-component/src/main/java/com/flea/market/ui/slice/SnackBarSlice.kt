package com.flea.market.ui.slice

import androidx.annotation.StringRes
import com.flea.market.ui.component.SnackbarDelegate.SnackbarType
import kotlinx.coroutines.flow.StateFlow

interface SnackBarSlice {
    val snackbarUiState: StateFlow<SnackBarDetails?>

    fun showSnackBar(snackBarDetails: SnackBarDetails)
    fun onSnackbarResult(isActionPerformed: Boolean)

    data class SnackBarDetails(
        @StringRes val message: Int,
        @StringRes val actionLabel: Int? = null,
        val snackbarType: SnackbarType = SnackbarType.DEFAULT,
        val onActionPerformed: (() -> Unit)? = null
    )
}