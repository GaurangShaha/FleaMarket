package com.flea.market.cart.ui.details.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.flea.market.cart.ui.details.CartDetailsIntent
import com.flea.market.cart.ui.details.CartDetailsIntent.DecreaseQuantity
import com.flea.market.cart.ui.details.CartDetailsIntent.IncreaseQuantity
import com.flea.market.cart.ui.details.CartDetailsIntent.RemoveFromCart
import com.flea.market.cart.ui.details.CartDetailsUiState.Content
import com.flea.market.common.navigation.ProductDetailsDestination
import com.flea.market.ui.compositionlocal.LocalNavController
import com.flea.market.ui.compositionlocal.LocalWindowSizeClass

@Composable
internal fun CartDetailsContent(uiState: Content, processIntent: (CartDetailsIntent) -> Unit) {
    if (LocalWindowSizeClass.current.widthSizeClass == WindowWidthSizeClass.Compact) {
        ContentForCompactScreen(uiState = uiState, processIntent = processIntent)
    } else {
        ContentForMediumAndExpandedScreen(uiState = uiState, processIntent = processIntent)
    }
}

internal const val FIRST_COLUMN_WEIGHT_LARGE_SCREENS = 0.4f
internal const val SECOND_COLUMN_WEIGHT_LARGE_SCREENS = 1 - FIRST_COLUMN_WEIGHT_LARGE_SCREENS

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun ContentForMediumAndExpandedScreen(
    uiState: Content,
    processIntent: (CartDetailsIntent) -> Unit
) {
    Row {
        LazyColumn(
            modifier = Modifier
                .fillMaxHeight()
                .weight(FIRST_COLUMN_WEIGHT_LARGE_SCREENS),
            contentPadding = PaddingValues(bottom = 8.dp)
        ) {
            stickyHeader(contentType = "Checkout") {
                CheckoutButton { }
            }

            item(contentType = "PriceDetails") {
                PriceDetails(priceDetails = uiState.priceDetails)
            }
        }

        LazyColumn(
            modifier = Modifier.weight(SECOND_COLUMN_WEIGHT_LARGE_SCREENS),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(bottom = 8.dp)
        ) {
            items(key = { it.id }, items = uiState.productList) { itemsInCartViewEntity ->
                val navController = LocalNavController.current
                CartItemProductDetails(
                    itemsInCart = itemsInCartViewEntity,
                    onDecreaseQuantity = { processIntent(DecreaseQuantity(itemsInCartViewEntity)) },
                    onIncreaseQuantity = { processIntent(IncreaseQuantity(itemsInCartViewEntity)) },
                    onRemoveFromCart = { processIntent(RemoveFromCart(itemsInCartViewEntity)) },
                    modifier = Modifier.animateItem()
                ) { navController.navigate(ProductDetailsDestination(itemsInCartViewEntity.id)) }
            }
        }
    }
}

@Composable
@OptIn(ExperimentalFoundationApi::class)
private fun ContentForCompactScreen(uiState: Content, processIntent: (CartDetailsIntent) -> Unit) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(top = 16.dp, bottom = 90.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        item(contentType = "PriceDetails") {
            PriceDetails(priceDetails = uiState.priceDetails)
        }

        stickyHeader(contentType = "Checkout") {
            CheckoutButton { }
        }

        items(
            key = { it.id },
            items = uiState.productList,
            contentType = { "productItem" }
        ) { itemsInCartViewEntity ->
            val navController = LocalNavController.current
            CartItemProductDetails(
                modifier = Modifier.animateItem(),
                itemsInCart = itemsInCartViewEntity,
                onDecreaseQuantity = { processIntent(DecreaseQuantity(itemsInCartViewEntity)) },
                onIncreaseQuantity = { processIntent(IncreaseQuantity(itemsInCartViewEntity)) },
                onRemoveFromCart = { processIntent(RemoveFromCart(itemsInCartViewEntity)) },
            ) { navController.navigate(ProductDetailsDestination(itemsInCartViewEntity.id)) }
        }
    }
}
