package com.flea.product.ui.details.component

import com.flea.common.ui.component.ButtonState
import com.flea.product.ui.details.ProductDetailsUiState
import com.flea.product.ui.list.component.dummyProductList
import kotlinx.coroutines.flow.MutableStateFlow

internal val dummyProductDetails = dummyProductList.first()

internal val dummyContent = ProductDetailsUiState.Content(
    productDetails = dummyProductDetails,
    markedAsFavouriteFlow = MutableStateFlow(false),
    addToCartButtonState = MutableStateFlow(ButtonState.Initial)
)