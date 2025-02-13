package com.flea.market.profile.ui

import androidx.compose.runtime.Composable
import com.flea.market.ui.preview.FleaMarketPreviews
import com.flea.market.ui.preview.FleaMarketThemePreview

@Composable
@FleaMarketPreviews
internal fun ProfileScreenPreview() {
    FleaMarketThemePreview {
        ProfileScreen(ProfileUiState())
    }
}