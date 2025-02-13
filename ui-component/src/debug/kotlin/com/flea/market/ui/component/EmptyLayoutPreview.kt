package com.flea.market.ui.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.flea.market.ui.preview.FleaMarketPreviews
import com.flea.market.ui.preview.FleaMarketThemePreview

@FleaMarketPreviews
@Composable
private fun EmptyLayoutPreview() {
    FleaMarketThemePreview {
        EmptyLayout(
            message = stringResource(id = R.string.retry),
            icon = painterResource(id = R.drawable.ic_network_error)
        )
    }
}