package com.flea.market.favorite.ui.list.component

import com.flea.market.favorite.ui.list.FavouriteListUiState
import com.flea.market.favorite.ui.list.entity.FavouriteItemViewEntity
import kotlinx.coroutines.flow.MutableStateFlow

@Suppress("MaxLineLength")
internal val dummyFavouriteItemList = listOf(
    FavouriteItemViewEntity(
        title = "Mens Cotton Jacket",
        id = 0,
        price = 55.99,
        description = "great outerwear jackets for Spring/Autumn/Winter, suitable for many occasions, such as working, hiking, camping, mountain/rock climbing, cycling, traveling or other outdoors. Good gift choice for you or your family member. A warm hearted love to Father, husband or son in this thanksgiving or Christmas Day.",
        category = "Men's clothing",
        image = "https://fakestoreapi.com/img/71li-ujtlUL._AC_UX679_.jpg",
        rating = 4.7
    ), FavouriteItemViewEntity(
        title = "Womens Cotton Jacket",
        id = 1,
        price = 50.99,
        description = "great outerwear jackets for Spring/Autumn/Winter, suitable for many occasions, such as working, hiking, camping, mountain/rock climbing, cycling, traveling or other outdoors. Good gift choice for you or your family member. A warm hearted love to Father, husband or son in this thanksgiving or Christmas Day.",
        category = "Women's clothing",
        image = "https://fakestoreapi.com/img/71li-ujtlUL._AC_UX679_.jpg",
        rating = 4.0
    ), FavouriteItemViewEntity(
        title = "Womens Woolen Jacket",
        id = 2,
        price = 70.99,
        description = "great outerwear jackets for Spring/Autumn/Winter, suitable for many occasions, such as working, hiking, camping, mountain/rock climbing, cycling, traveling or other outdoors. Good gift choice for you or your family member. A warm hearted love to Father, husband or son in this thanksgiving or Christmas Day.",
        category = "Women's clothing",
        image = "https://fakestoreapi.com/img/71li-ujtlUL._AC_UX679_.jpg",
        rating = 4.0
    )
)

internal val dummyContent =
    FavouriteListUiState.Content(dummyFavouriteItemList, MutableStateFlow(null))