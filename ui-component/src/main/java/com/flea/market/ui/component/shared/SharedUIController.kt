package com.flea.market.ui.component.shared

import com.flea.market.ui.component.snackbar.model.SnackbarDetails

public interface SharedUIController {
    public fun showSnackbar(snackbarDetails: SnackbarDetails)
    public fun resetSnackbarDetails()
}
