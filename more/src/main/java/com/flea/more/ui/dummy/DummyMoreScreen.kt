package com.flea.more.ui.dummy

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.flea.common.ui.component.preview.FleaMarketPreview
import com.flea.common.ui.component.preview.FleaMarketThemePreview

@Composable
internal fun DummyMore() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(
            text = "Dummy More"
        )
    }
}

@Composable
@FleaMarketPreview
internal fun DummyMorePreview() {
    FleaMarketThemePreview {
        DummyMore()
    }
}