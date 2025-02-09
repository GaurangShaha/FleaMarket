package com.flea.market.ui.main

import com.flea.market.ui.component.snackbar.model.SnackbarDetails

internal sealed class MainIntent {
    data class UpdateSelectedNavigationItemIndex(val index: Int) : MainIntent()
    data class ShowSnackbar(val snackbarDetails: SnackbarDetails) : MainIntent()
    data object ResetSnackbarDetails : MainIntent()
}
