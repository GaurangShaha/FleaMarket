package com.flea.product.ui.navigation

import androidx.navigation.NavGraphBuilder
import com.flea.product.ui.details.navigation.productDetailsScreen
import com.flea.product.ui.list.navigation.productListScreen

fun NavGraphBuilder.addProductGraph() {
    productListScreen()
    productDetailsScreen()
}