package com.flea.common.ui.slice

import androidx.annotation.StringRes
import com.flea.common.ui.app.state.SnackbarDelegate.SnackbarType
import com.flea.common.ui.base.viewmodel.BaseViewModelSlice
import com.flea.common.ui.slice.SnackBarSlice.SnackBarDetails
import kotlinx.coroutines.flow.StateFlow

interface SnackBarSlice {
    val snackbarUiState: StateFlow<SnackBarDetails?>

    fun showSnackBar(snackBarDetails: SnackBarDetails)
    fun notifySnackbarResult(isActionPerformed: Boolean)

    data class SnackBarDetails(
        @StringRes val message: Int,
        @StringRes val actionLabel: Int? = null,
        val snackbarType: SnackbarType = SnackbarType.DEFAULT,
        val onActionPerformed: (() -> Unit)? = null
    )
}

class SnackBarSliceImpl : BaseViewModelSlice<SnackBarDetails?>(null), SnackBarSlice {
    override val snackbarUiState = sliceUiState

    private var onActionPerformed: (() -> Unit)? = null

    override fun showSnackBar(snackBarDetails: SnackBarDetails) {
        this.onActionPerformed = snackBarDetails.onActionPerformed
        updateSliceUiState(snackBarDetails)
    }

    override fun notifySnackbarResult(isActionPerformed: Boolean) {
        updateSliceUiState(null)
        if (isActionPerformed) {
            onActionPerformed?.invoke()
        }
    }
}