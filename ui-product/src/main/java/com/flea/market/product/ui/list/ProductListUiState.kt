package com.flea.market.product.ui.list

import com.flea.market.product.ui.common.entity.ProductDetailsViewEntity

internal sealed class ProductListUiState {
    object Loading : ProductListUiState()
    class Error(val throwable: Throwable) : ProductListUiState()
    data class Content(
        val productList: List<ProductDetailsViewEntity>,
        val categoryListWrapper: CategoryListImmutableWrapper,
        val selectedCategoryIndex: Int
    ) : ProductListUiState()
}
