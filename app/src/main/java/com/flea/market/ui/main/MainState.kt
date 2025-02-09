package com.flea.market.ui.main

import com.flea.market.ui.component.snackbar.model.SnackbarDetails

internal data class MainState(
    val selectedNavigationItemIndex: Int,
    val snackbarDetails: SnackbarDetails?
)
