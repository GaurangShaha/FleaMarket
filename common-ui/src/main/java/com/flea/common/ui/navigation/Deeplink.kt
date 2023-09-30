package com.flea.common.ui.navigation

import androidx.core.net.toUri
import androidx.navigation.NavDeepLink
import androidx.navigation.navDeepLink
import com.flea.common.ui.navigation.Deeplink.Companion.INTERNAL_DEEPLINK_BASE_URI

sealed interface Deeplink {
    fun navDeeplink(): NavDeepLink

    companion object {
        internal const val INTERNAL_DEEPLINK_BASE_URI = "app://fleamarket/"
    }
}

object ProductDetailsDeepLink : Deeplink {
    const val PRODUCT_DETAILS_DEEPLINK_ARGUMENT_PRODUCT_ID = "productId"
    private const val PRODUCT_DETAILS_DEEPLINK_BY_ID = "product_details/%d"
    private const val PRODUCT_DETAILS_DEEPLINK =
        INTERNAL_DEEPLINK_BASE_URI + "product_details/{$PRODUCT_DETAILS_DEEPLINK_ARGUMENT_PRODUCT_ID}"

    override fun navDeeplink() = navDeepLink { uriPattern = PRODUCT_DETAILS_DEEPLINK }

    fun getUri(productId: Int) =
        (INTERNAL_DEEPLINK_BASE_URI + PRODUCT_DETAILS_DEEPLINK_BY_ID.format(productId)).toUri()
}