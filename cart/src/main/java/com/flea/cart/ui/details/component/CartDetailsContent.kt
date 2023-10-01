package com.flea.cart.ui.details.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.flea.cart.ui.details.CartDetailsIntent
import com.flea.cart.ui.details.CartDetailsIntent.DecreaseQuantity
import com.flea.cart.ui.details.CartDetailsIntent.IncreaseQuantity
import com.flea.cart.ui.details.CartDetailsIntent.RemoveFromCart
import com.flea.cart.ui.details.CartDetailsUiState.Content
import com.flea.common.ui.component.compositionlocal.LocalNavControllerProvider
import com.flea.common.ui.component.compositionlocal.LocalWindowSizeClass
import com.flea.common.ui.component.preview.FleaMarketPreview
import com.flea.common.ui.component.preview.FleaMarketThemePreview
import com.flea.common.ui.navigation.ProductDetailsDeepLink

@Composable
internal fun CartDetailsContent(uiState: Content, handleIntent: (CartDetailsIntent) -> Unit) {
    if (LocalWindowSizeClass.current.widthSizeClass == WindowWidthSizeClass.Compact) {
        ContentForCompactScreen(uiState = uiState, handleIntent = handleIntent)
    } else {
        ContentForMediumAndExpandedScreen(uiState = uiState, handleIntent = handleIntent)
    }

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun ContentForMediumAndExpandedScreen(
    uiState: Content, handleIntent: (CartDetailsIntent) -> Unit
) {
    Row {
        LazyColumn(
            modifier = Modifier
                .fillMaxHeight()
                .weight(0.4f),
            contentPadding = PaddingValues(bottom = 8.dp)
        ) {
            stickyHeader(contentType = "Checkout") {
                CheckoutButton { handleIntent(CartDetailsIntent.Checkout) }
            }

            item(contentType = "PriceDetails") {
                PriceDetails(priceDetails = uiState.priceDetails)
            }
        }

        LazyColumn(
            modifier = Modifier.weight(0.6f),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(bottom = 8.dp)
        ) {
            items(key = { it.id }, items = uiState.productList) { itemsInCartViewEntity ->
                val navController = LocalNavControllerProvider.current
                CartItemProductDetails(modifier = Modifier.animateItemPlacement(),
                    itemsInCart = itemsInCartViewEntity,
                    decreaseQuantity = { handleIntent(DecreaseQuantity(it)) },
                    increaseQuantity = { handleIntent(IncreaseQuantity(it)) },
                    removeFromCart = { handleIntent(RemoveFromCart(it)) },
                    navigateToProductDetails = {
                        navController.navigate(ProductDetailsDeepLink.getUri(it))
                    })
            }
        }
    }
}

@Composable
@OptIn(ExperimentalFoundationApi::class)
private fun ContentForCompactScreen(uiState: Content, handleIntent: (CartDetailsIntent) -> Unit) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(top = 16.dp, bottom = 90.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        item(contentType = "PriceDetails") {
            PriceDetails(priceDetails = uiState.priceDetails)
        }

        stickyHeader(contentType = "Checkout") {
            CheckoutButton { handleIntent(CartDetailsIntent.Checkout) }
        }

        items(key = { it.id },
            items = uiState.productList,
            contentType = { "productItem" }) { itemsInCartViewEntity ->
            val navController = LocalNavControllerProvider.current
            CartItemProductDetails(modifier = Modifier.animateItemPlacement(),
                itemsInCart = itemsInCartViewEntity,
                decreaseQuantity = { handleIntent(DecreaseQuantity(it)) },
                increaseQuantity = { handleIntent(IncreaseQuantity(it)) },
                removeFromCart = { handleIntent(RemoveFromCart(it)) },
                navigateToProductDetails = { navController.navigate(ProductDetailsDeepLink.getUri(it)) })
        }
    }
}


@Composable
@FleaMarketPreview
private fun CartDetailsContentPreview() {
    FleaMarketThemePreview {
        CartDetailsContent(uiState = dummyContent, handleIntent = {})
    }
}