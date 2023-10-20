package com.flea.market.common.slice

import com.flea.market.common.base.viewmodel.BaseViewModelSlice
import com.flea.market.ui.slice.SnackBarSlice
import com.flea.market.ui.slice.SnackBarSlice.SnackBarDetails

class SnackBarSliceImpl : BaseViewModelSlice<SnackBarDetails?>(null), SnackBarSlice {
    override val snackbarUiState = sliceUiState

    private var onActionPerformed: (() -> Unit)? = null

    override fun showSnackBar(snackBarDetails: SnackBarDetails) {
        this.onActionPerformed = snackBarDetails.onActionPerformed
        updateSliceUiState(snackBarDetails)
    }

    override fun onSnackbarResult(isActionPerformed: Boolean) {
        updateSliceUiState(null)
        if (isActionPerformed) {
            onActionPerformed?.invoke()
        }
    }
}