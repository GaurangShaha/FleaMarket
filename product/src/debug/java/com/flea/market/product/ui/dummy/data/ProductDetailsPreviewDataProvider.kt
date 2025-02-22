package com.flea.market.product.ui.dummy.data

import android.artisan.ui.component.ButtonState
import com.flea.market.product.ui.common.entity.ProductDetailsViewEntity
import com.flea.market.product.ui.details.ProductDetailsUiState.Content
import com.flea.market.product.ui.list.CategoryListImmutableWrapper
import com.flea.market.product.ui.list.ProductListUiState

@Suppress("MaxLineLength")
internal val dummyProductList = listOf(
    ProductDetailsViewEntity(
        title = "Mens Cotton Jacket",
        id = 0,
        price = 55.99,
        description = "great outerwear jackets for Spring/Autumn/Winter, suitable for many occasions, such as working, hiking, camping, mountain/rock climbing, cycling, traveling or other outdoors. Good gift choice for you or your family member. A warm hearted love to Father, husband or son in this thanksgiving or Christmas Day.",
        category = "Men's clothing",
        imageList = listOf(
            "https://fakestoreapi.com/img/71li-ujtlUL._AC_UX679_.jpg",
            "https://fakestoreapi.com/img/71li-ujtlUL._AC_UX679_.jpg"
        ),
        count = 500,
        rate = 4.7
    ),
    ProductDetailsViewEntity(
        title = "Womens Cotton Jacket",
        id = 1,
        price = 50.99,
        description = "great outerwear jackets for Spring/Autumn/Winter, suitable for many occasions, such as working, hiking, camping, mountain/rock climbing, cycling, traveling or other outdoors. Good gift choice for you or your family member. A warm hearted love to Father, husband or son in this thanksgiving or Christmas Day.",
        category = "Women's clothing",
        imageList = listOf("https://fakestoreapi.com/img/71li-ujtlUL._AC_UX679_.jpg"),
        count = 500,
        rate = 4.0
    )
)

internal val dummyCategoryListWrapper =
    CategoryListImmutableWrapper(
        listOf(
            "All",
            "Men's clothing",
            "Women's clothing",
            "Jewelry",
            "Electronics"
        )
    )

internal val dummyProductListContent =
    ProductListUiState.Content(
        dummyProductList,
        dummyCategoryListWrapper,
        0
    )

internal val dummyProductDetails = dummyProductList.first()

internal val dummyContent = Content(
    productDetails = dummyProductDetails,
    markedAsFavourite = false,
    addToCartButtonState = ButtonState.Initial
)
