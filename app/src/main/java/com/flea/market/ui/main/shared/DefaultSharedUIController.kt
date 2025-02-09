package com.flea.market.ui.main.shared

import com.flea.market.ui.component.shared.SharedUIController
import com.flea.market.ui.component.snackbar.model.SnackbarDetails
import com.flea.market.ui.main.MainIntent
import com.flea.market.ui.main.MainIntent.ResetSnackbarDetails
import com.flea.market.ui.main.MainIntent.ShowSnackbar
import com.flea.market.ui.main.MainIntent.UpdateSelectedNavigationItemIndex

internal class DefaultSharedUIController(private val onHandleIntent: (MainIntent) -> Unit) :
    SharedUIController {
    override fun showSnackbar(snackbarDetails: SnackbarDetails) {
        onHandleIntent(ShowSnackbar(snackbarDetails))
    }

    override fun resetSnackbarDetails() {
        onHandleIntent(ResetSnackbarDetails)
    }

    override fun updateSelectedNavigationItemIndex(index: Int) {
        onHandleIntent(UpdateSelectedNavigationItemIndex(index))
    }
}
