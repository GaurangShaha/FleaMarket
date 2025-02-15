package com.flea.market.ui.main.shared

import com.flea.market.ui.component.shared.SharedUIController
import com.flea.market.ui.component.snackbar.model.SnackbarDetails
import com.flea.market.ui.main.MainIntent
import com.flea.market.ui.main.MainIntent.ResetSnackbarDetails
import com.flea.market.ui.main.MainIntent.ShowSnackbar

internal class DefaultSharedUIController(private val processIntent: (MainIntent) -> Unit) :
    SharedUIController {
    override fun showSnackbar(snackbarDetails: SnackbarDetails) {
        processIntent(ShowSnackbar(snackbarDetails))
    }

    override fun resetSnackbarDetails() {
        processIntent(ResetSnackbarDetails)
    }
}
