package com.flea.market.profile.ui

import android.artisan.ui.preview.FleaMarketPreviews
import android.artisan.ui.preview.FleaMarketThemePreview
import androidx.compose.runtime.Composable

@Composable
@FleaMarketPreviews
internal fun ProfileScreenPreview() {
    FleaMarketThemePreview {
        ProfileScreen(ProfileUiState())
    }
}
