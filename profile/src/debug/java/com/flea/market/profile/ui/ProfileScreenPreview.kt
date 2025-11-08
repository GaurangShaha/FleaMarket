package com.flea.market.profile.ui

import androidx.compose.runtime.Composable
import building.blocks.ui.preview.FleaMarketPreviews
import building.blocks.ui.preview.FleaMarketThemePreview

@Composable
@FleaMarketPreviews
internal fun ProfileScreenPreview() {
    FleaMarketThemePreview {
        ProfileScreen(ProfileUiState())
    }
}
