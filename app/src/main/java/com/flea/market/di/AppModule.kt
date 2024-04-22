package com.flea.market.di

import com.flea.market.cart.data.di.cartDataModule
import com.flea.market.cart.ui.di.cartPresentationModule
import com.flea.market.data.di.dbModule
import com.flea.market.data.di.networkModule
import com.flea.market.favorite.ui.di.favouritePresentationModule
import com.flea.market.favourite.di.favouriteDataModule
import com.flea.market.product.data.di.productDataModule
import com.flea.market.product.ui.di.productPresentationModule
import com.flea.market.profile.ui.di.profilePresentationModule

private val presentationModules = listOf(
    productPresentationModule,
    cartPresentationModule,
    favouritePresentationModule,
    homePresentationModule,
    profilePresentationModule
)

private val dataModules = listOf(
    productDataModule,
    cartDataModule,
    favouriteDataModule
)

internal val appModule = presentationModules + dataModules + networkModule + dbModule
