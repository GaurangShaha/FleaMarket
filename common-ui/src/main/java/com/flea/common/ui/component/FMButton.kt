package com.flea.common.ui.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.flea.common.ui.theme.extraShape

@Composable
fun FMButton(
    modifier: Modifier = Modifier, text: String, onClick: () -> Unit
) {
    Button(modifier = modifier, shape = MaterialTheme.extraShape.capsuleShape, onClick = onClick) {
        Text(modifier = Modifier.padding(6.dp), text = text)
    }
}
