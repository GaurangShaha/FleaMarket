package com.flea.market.product.ui.details.component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import building.blocks.ui.component.AddToCartButton
import building.blocks.ui.component.ButtonState
import building.blocks.ui.theme.extraColors
import building.blocks.ui.theme.extraShape
import com.flea.market.product.ui.R

@Composable
internal fun AddToCart(
    buttonState: ButtonState,
    modifier: Modifier = Modifier,
    onAddToCart: () -> Unit
) {
    AddToCartButton(
        buttonState = buttonState,
        initialContent = {
            Icon(imageVector = Icons.Default.Add, contentDescription = null)
            Spacer(modifier = Modifier.size(8.dp))
            Text(
                text = stringResource(id = R.string.add_to_cart),
                style = MaterialTheme.typography.button
            )
        },
        resultContent = {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = null,
                tint = MaterialTheme.extraColors.successColor
            )
            Spacer(modifier = Modifier.size(8.dp))
            Text(
                text = stringResource(id = R.string.added_to_cart),
                style = MaterialTheme.typography.button
            )
        },
        onClick = onAddToCart,
        modifier = modifier,
        shape = MaterialTheme.extraShape.capsuleShape,
        contentPadding = PaddingValues(14.dp)
    )
}
