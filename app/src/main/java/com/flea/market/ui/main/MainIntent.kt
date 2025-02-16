package com.flea.market.ui.main

import android.artisan.ui.component.snackbar.model.SnackbarDetails

internal sealed class MainIntent {
    data class ShowSnackbar(val snackbarDetails: SnackbarDetails) : MainIntent()
    data object ResetSnackbarDetails : MainIntent()
}
