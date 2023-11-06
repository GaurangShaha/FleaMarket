package com.flea.market.common.slice

import com.flea.market.common.base.viewmodel.BaseViewModelSlice
import com.flea.market.ui.component.SnackBarDetails

class SnackBarSlice : BaseViewModelSlice<SnackBarDetails?>(null) {
    val snackbarUiState = sliceUiState

    private var onActionPerformed: (() -> Unit)? = null

    fun showSnackBar(snackBarDetails: SnackBarDetails) {
        this.onActionPerformed = snackBarDetails.onActionPerformed
        updateSliceUiState(snackBarDetails)
    }

    fun onSnackbarResult(isActionPerformed: Boolean) {
        updateSliceUiState(null)
        if (isActionPerformed) {
            onActionPerformed?.invoke()
        }
    }
}
