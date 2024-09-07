package com.flea.market.cart.ui.details.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.flea.market.cart.ui.R
import com.flea.market.ui.component.FMButton

@Composable
internal fun CheckoutButton(modifier: Modifier = Modifier, onCheckout: () -> Unit) {
    Box(
        modifier
            .background(MaterialTheme.colors.surface)
            .padding(start = 16.dp, end = 16.dp, bottom = 8.dp)
    ) {
        FMButton(
            text = stringResource(id = R.string.checkout),
            modifier = Modifier.fillMaxWidth(),
            onClick = onCheckout
        )
    }
}
