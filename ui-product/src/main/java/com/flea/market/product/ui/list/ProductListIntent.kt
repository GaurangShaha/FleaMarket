package com.flea.market.product.ui.list

internal sealed class ProductListIntent {
    object Reload : ProductListIntent()
    data class FilterByCategory(val selectedCategoryIndex: Int) : ProductListIntent()
}
