package com.flea.common.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.flea.common.ui.R
import com.flea.common.ui.component.preview.FleaMarketPreview
import com.flea.common.ui.component.preview.FleaMarketThemePreview
import com.flea.common.ui.theme.extraShape

@Composable
fun Stepper(
    modifier: Modifier = Modifier,
    quantity: Int,
    decreaseQuantity: () -> Unit,
    increaseQuantity: () -> Unit
) {
    Row(
        modifier = modifier
            .border(
                border = BorderStroke(
                    width = 1.dp, color = MaterialTheme.colors.primary
                ), shape = MaterialTheme.extraShape.capsuleShape
            )
            .padding(start = 8.dp, end = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(modifier = Modifier.size(28.dp), onClick = decreaseQuantity) {
            Icon(
                modifier = Modifier.padding(8.dp),
                painter = painterResource(id = R.drawable.ic_minus),
                contentDescription = null
            )
        }

        Text(text = quantity.toString())

        IconButton(modifier = Modifier.size(28.dp), onClick = increaseQuantity) {
            Icon(
                modifier = Modifier.padding(5.dp),
                imageVector = Icons.Rounded.Add,
                contentDescription = null
            )
        }
    }
}

@Composable
@FleaMarketPreview
fun StepperPreview() {
    FleaMarketThemePreview {
        Stepper(quantity = 0, decreaseQuantity = { }, increaseQuantity = {})
    }
}