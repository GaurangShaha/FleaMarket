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
import com.flea.market.component.R
import com.flea.market.ui.preview.FleaMarketPreview
import com.flea.market.ui.preview.FleaMarketThemePreview

@Composable
fun ErrorLayout(
    modifier: Modifier = Modifier,
    errorMessage: String,
    errorIcon: Painter,
    retry: (() -> Unit)? = null
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = errorIcon,
            contentDescription = null,
            modifier = Modifier.size(82.dp)
        )

        Spacer(modifier = Modifier.size(24.dp))

        Text(
            text = errorMessage,
            textAlign = TextAlign.Center,

            )

        Spacer(modifier = Modifier.size(14.dp))

        retry?.let {
            FMButton(
                text = stringResource(R.string.retry),
                onClick = it
            )
        }
    }
}

@FleaMarketPreview
@Composable
private fun ErrorLayoutPreview() {
    FleaMarketThemePreview {
        ErrorLayout(
            errorMessage = stringResource(id = R.string.retry),
            errorIcon = painterResource(id = R.drawable.ic_network_error)
        ) {}
    }
}