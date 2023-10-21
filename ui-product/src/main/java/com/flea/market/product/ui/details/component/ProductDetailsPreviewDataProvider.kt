package com.flea.market.product.ui.details.component

import com.flea.market.product.ui.details.ProductDetailsUiState.Content
import com.flea.market.product.ui.list.component.dummyProductList
import com.flea.market.ui.component.ButtonState
import kotlinx.coroutines.flow.MutableStateFlow

internal val dummyProductDetails = dummyProductList.first()

internal val dummyContent = Content(
    productDetails = dummyProductDetails,
    markedAsFavouriteFlow = MutableStateFlow(false),
    addToCartButtonState = MutableStateFlow(ButtonState.Initial)
)
