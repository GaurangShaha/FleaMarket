package com.flea.market.cart.ui.dummy.data

import com.flea.market.cart.ui.details.CartDetailsUiState
import com.flea.market.cart.ui.details.entity.ItemsInCartViewEntity
import com.flea.market.cart.ui.details.entity.PriceDetailsViewEntity

internal val dummyItemsInCartList = listOf(
    ItemsInCartViewEntity(
        title = "Mens Cotton Jacket",
        id = 0,
        price = 55.99,
        category = "Men's clothing",
        image = "https://fakestoreapi.com/img/71li-ujtlUL._AC_UX679_.jpg",
        quantity = 10
    ),
    ItemsInCartViewEntity(
        title = "Womens Cotton Jacket",
        id = 1,
        price = 35.99,
        category = "Women's clothing",
        image = "https://fakestoreapi.com/img/71li-ujtlUL._AC_UX679_.jpg",
        quantity = 1
    )
)

internal val dummyPriceDetails = PriceDetailsViewEntity(
    subTotal = 100.toDouble(),
    discount = 10.toDouble(),
    tax = 12.toDouble(),
    totalPayable = 102.toDouble()
)

internal val dummyContent = CartDetailsUiState.Content(
    productList = dummyItemsInCartList,
    priceDetails = dummyPriceDetails
)
