package com.flea.product.ui.list

import com.flea.product.ui.common.entity.ProductDetailsViewEntity

internal sealed class ProductListUiState {
    object Loading : ProductListUiState()
    class Error(val throwable: Throwable) : ProductListUiState()
    data class Content(
        val productList: List<ProductDetailsViewEntity>,
        val categoryList: List<String>,
        val selectedCategoryIndex: Int
    ) : ProductListUiState()
}
