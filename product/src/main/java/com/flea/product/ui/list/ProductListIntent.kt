package com.flea.product.ui.list

internal sealed class ProductListIntent {
    object Reload : ProductListIntent()
    data class FilterByCategory(val selectedCategoryIndex: Int) : ProductListIntent()
}
