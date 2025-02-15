package com.flea.market.product.ui.details.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.flea.market.product.ui.details.ProductDetailsIntent
import com.flea.market.product.ui.details.ProductDetailsIntent.AddToCart
import com.flea.market.product.ui.details.ProductDetailsUiState.Content
import com.flea.market.ui.compositionlocal.LocalWindowSizeClass
import com.flea.market.ui.theme.extraShape

@Composable
internal fun ProductDetailsContent(
    state: Content,
    processIntent: (ProductDetailsIntent) -> Unit
) {
    if (LocalWindowSizeClass.current.widthSizeClass == WindowWidthSizeClass.Compact ||
        LocalWindowSizeClass.current.widthSizeClass == WindowWidthSizeClass.Medium
    ) {
        ContentForCompactScreen(state, processIntent)
    } else {
        ContentForMediumAndExpandedScreen(state, processIntent)
    }
}

internal const val DELAY_FOR_SWITCHING_IMAGE: Long = 8000

@Composable
private fun ContentForMediumAndExpandedScreen(
    state: Content,
    processIntent: (ProductDetailsIntent) -> Unit
) {
    Row {
        PagerWithIndicator(
            uiState = state,
            modifier = Modifier.fillMaxHeight(),
            delayForSwitchingImage = DELAY_FOR_SWITCHING_IMAGE
        )

        Box(
            modifier = Modifier
                .weight(1f)
                .background(MaterialTheme.colors.secondary),
            contentAlignment = Alignment.BottomCenter
        ) {
            ProductInformation(
                state = state,
                modifier = Modifier
                    .fillMaxHeight()
                    .verticalScroll(rememberScrollState())
                    .padding(start = 16.dp, end = 16.dp, bottom = 70.dp, top = 72.dp)
            )
            AddToCart(
                buttonState = state.addToCartButtonState,
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
            ) { processIntent(AddToCart) }
        }
    }
}

@Composable
private fun ContentForCompactScreen(
    state: Content,
    processIntent: (ProductDetailsIntent) -> Unit
) {
    Box(contentAlignment = Alignment.BottomCenter) {
        val scrollState = rememberScrollState()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colors.secondary)
                .verticalScroll(scrollState)
                .padding(bottom = 64.dp),
            verticalArrangement = Arrangement.spacedBy(-(32.dp))
        ) {
            PagerWithIndicator(
                uiState = state,
                modifier = Modifier.fillMaxWidth(),
                delayForSwitchingImage = DELAY_FOR_SWITCHING_IMAGE,
                scrollState = scrollState
            )

            Surface(
                shape = MaterialTheme.extraShape.xlargeShape,
                color = MaterialTheme.colors.secondary,
                modifier = Modifier.fillMaxSize()
            ) {
                ProductInformation(state = state, modifier = Modifier.padding(16.dp))
            }
        }
        AddToCart(
            buttonState = state.addToCartButtonState,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) { processIntent(AddToCart) }
    }
}
