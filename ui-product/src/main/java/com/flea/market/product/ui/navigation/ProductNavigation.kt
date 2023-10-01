package com.flea.market.product.ui.navigation

import androidx.navigation.NavGraphBuilder
import com.flea.market.product.ui.details.navigation.productDetailsScreen
import com.flea.market.product.ui.list.navigation.productListScreen

fun NavGraphBuilder.addProductGraph() {
    productListScreen()
    productDetailsScreen()
}