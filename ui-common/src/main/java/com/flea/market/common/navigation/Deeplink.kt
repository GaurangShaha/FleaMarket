package com.flea.market.common.navigation

import android.net.Uri
import androidx.core.net.toUri
import androidx.navigation.NavDeepLink
import androidx.navigation.navDeepLink
import com.flea.market.common.navigation.Deeplink.Companion.INTERNAL_DEEPLINK_BASE_URI

public sealed interface Deeplink {
    public fun navDeeplink(): NavDeepLink

    public companion object {
        internal const val INTERNAL_DEEPLINK_BASE_URI = "app://fleamarket/"
    }
}

public object ProductDetailsDeepLink : Deeplink {
    public const val PRODUCT_DETAILS_DEEPLINK_ARGUMENT_PRODUCT_ID: String = "productId"
    private const val PRODUCT_DETAILS_DEEPLINK_BY_ID = "product_details/%d"
    private const val PRODUCT_DETAILS_DEEPLINK =
        INTERNAL_DEEPLINK_BASE_URI + "product_details/{$PRODUCT_DETAILS_DEEPLINK_ARGUMENT_PRODUCT_ID}"

    override fun navDeeplink(): NavDeepLink = navDeepLink { uriPattern = PRODUCT_DETAILS_DEEPLINK }

    public fun getUri(productId: Int): Uri =
        (INTERNAL_DEEPLINK_BASE_URI + PRODUCT_DETAILS_DEEPLINK_BY_ID.format(productId)).toUri()
}
