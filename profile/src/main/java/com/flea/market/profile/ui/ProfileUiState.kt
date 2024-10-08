package com.flea.market.profile.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Build
import androidx.compose.material.icons.outlined.Call
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector

internal data class ProfileUiState(
    val profileUrl: String = "https://miro.medium.com/v2/1*sVI9YLq7y81EAgFzndU5Yg.jpeg",
    val name: String = "Gaurang Shaha",
    val email: String = "gaurang.shaha@gmail.com",
    val menuItem: List<Pair<ImageVector, Int>> = listOf(
        Icons.Outlined.AccountCircle to R.string.profile,
        Icons.AutoMirrored.Filled.List to R.string.payment_methods,
        Icons.Outlined.ShoppingCart to R.string.order_history,
        Icons.Outlined.LocationOn to R.string.delivery_address,
        Icons.Outlined.Call to R.string.support_center,
        Icons.Outlined.Build to R.string.settings
    )
)
