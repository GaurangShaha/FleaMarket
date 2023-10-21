package com.flea.market.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.flea.market.ui.preview.FleaMarketPreviews
import com.flea.market.ui.preview.FleaMarketThemePreview

@Composable
fun EmptyLayout(
    message: String,
    icon: Painter,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = icon,
            contentDescription = null,
            modifier = Modifier.size(82.dp)
        )

        Spacer(modifier = Modifier.size(24.dp))

        Text(text = message, textAlign = TextAlign.Center)
    }
}

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
