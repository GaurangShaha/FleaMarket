package com.flea.market.ui.main.shared

import building.blocks.ui.component.shared.SharedUIController
import building.blocks.ui.component.snackbar.model.SnackbarDetails
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
