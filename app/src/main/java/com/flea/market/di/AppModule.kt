package com.flea.market.di

import com.flea.market.cart.ui.di.cartPresentationModule
import com.flea.market.data.di.dataModules
import com.flea.market.favorite.ui.di.favouritePresentationModule
import com.flea.market.product.ui.di.productPresentationModule

private val presentationModules =
    productPresentationModule + cartPresentationModule + favouritePresentationModule + homePresentationModule

internal val appModule = dataModules + presentationModules
