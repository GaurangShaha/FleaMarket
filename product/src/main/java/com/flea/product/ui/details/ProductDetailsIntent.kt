package com.flea.product.ui.details

internal sealed class ProductDetailsIntent {
    object Reload : ProductDetailsIntent()
    object AddToCart : ProductDetailsIntent()
    class ToggleMarkAsFavourite(val markAsFavourite: Boolean) : ProductDetailsIntent()
}
