package com.flea.market.ui.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.flea.market.ui.component.ButtonState.Initial
import com.flea.market.ui.component.ButtonState.Loading
import com.flea.market.ui.component.ButtonState.Result
import com.flea.market.ui.preview.FleaMarketPreviews
import com.flea.market.ui.preview.FleaMarketThemePreview
import com.flea.market.ui.theme.extraColors
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@FleaMarketPreviews // Start interactive mode to see the animations
@Composable
private fun AddToCartButtonInitialPreview() {
    FleaMarketThemePreview {
        var buttonState: ButtonState by remember { mutableStateOf(Initial) }
        val coroutineScope = rememberCoroutineScope()

        AddToCartButton(
            buttonState = buttonState,
            initialContent = {
                Icon(imageVector = Icons.Default.Add, contentDescription = null)
                Spacer(modifier = Modifier.size(8.dp))
                Text(text = "ADD TO CART")
            },
            resultContent = {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = null,
                    tint = MaterialTheme.extraColors.successColor
                )
                Spacer(modifier = Modifier.size(8.dp))
                Text(text = "ADDED")
            },
            onClick = {
                buttonState = Loading
                coroutineScope.launch {
                    delay(2000)
                    buttonState = Result
                    delay(4000)
                    buttonState = Initial
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
    }
}
