package com.flea.market.di

import com.flea.market.cart.ui.di.cartPresentationModule
import com.flea.market.data.di.dataModules
import com.flea.market.favorite.ui.di.favouritePresentationModule
import com.flea.market.product.ui.di.productPresentationModule
import com.flea.market.profile.ui.di.profilePresentationModule

private val presentationModules = listOf(
    productPresentationModule,
    cartPresentationModule,
    favouritePresentationModule,
    homePresentationModule,
    profilePresentationModule
)

internal val appModule = dataModules + presentationModules
