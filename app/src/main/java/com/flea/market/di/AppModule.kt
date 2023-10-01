package com.flea.market.di

import com.flea.market.cart.ui.di.cartPresentationModule
import com.flea.market.data.cart.di.cartDataModule
import com.flea.market.data.common.di.baseDataModule
import com.flea.market.data.favourite.di.favouriteDataModule
import com.flea.market.data.product.di.productDataModule
import com.flea.market.favorite.ui.di.favouritePresentationModule
import com.flea.market.product.ui.di.productPresentationModule

private val presentationModules =
    productPresentationModule + cartPresentationModule + favouritePresentationModule + homePresentationModule

private val dataModules = productDataModule + cartDataModule + favouriteDataModule

internal val appModule = baseDataModule + presentationModules + dataModules