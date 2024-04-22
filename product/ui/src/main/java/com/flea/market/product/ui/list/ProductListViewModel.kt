package com.flea.market.product.ui.list

import androidx.lifecycle.viewModelScope
import com.flea.market.common.base.viewmodel.BaseViewModel
import com.flea.market.foundation.extension.fold
import com.flea.market.product.data.remote.entity.ProductDetailsEntity
import com.flea.market.product.data.repository.ProductRepository
import com.flea.market.product.ui.common.mapper.toCategoryListWrapper
import com.flea.market.product.ui.common.mapper.toProductDetailsViewEntity
import com.flea.market.product.ui.list.ProductListIntent.FilterByCategory
import com.flea.market.product.ui.list.ProductListIntent.Reload
import com.flea.market.product.ui.list.ProductListUiState.Content
import com.flea.market.product.ui.list.ProductListUiState.Error
import com.flea.market.product.ui.list.ProductListUiState.Loading
import kotlinx.coroutines.launch

internal class ProductListViewModel(private val productRepository: ProductRepository) :
    BaseViewModel<ProductListIntent, ProductListUiState>(Loading) {
    private var selectedCategoryIndex: Int = 0
    private lateinit var productList: List<com.flea.market.product.ui.common.entity.ProductDetailsViewEntity>

    init {
        getProductList()
    }

    override fun onHandleIntent(intent: ProductListIntent) {
        when (intent) {
            Reload -> getProductList()
            is FilterByCategory -> filterByCategory(intent.selectedCategoryIndex)
        }
    }

    private fun getProductList() {
        viewModelScope.launch {
            updateUiState(Loading)
            productRepository.getProductList()
                .fold(::handleProductListSuccess, ::handleProductListFailure)
        }
    }

    private fun filterByCategory(selectedCategoryIndex: Int) {
        this.selectedCategoryIndex = selectedCategoryIndex
        uiState.ifInstanceOf<Content> {
            filterProductByCategory(it.categoryListWrapper.items[selectedCategoryIndex])
        }
    }

    private fun handleProductListFailure(throwable: Throwable) {
        updateUiState(Error(throwable))
    }

    private fun handleProductListSuccess(productDetailsEntities: List<ProductDetailsEntity>) {
        productList = productDetailsEntities.map { it.toProductDetailsViewEntity() }
        updateUiState(
            Content(
                productList = productList,
                categoryListWrapper = productDetailsEntities.toCategoryListWrapper(),
                selectedCategoryIndex = selectedCategoryIndex
            )
        )
    }

    private fun filterProductByCategory(category: String) {
        uiState.ifInstanceOf<Content> { content ->
            val filteredList =
                productList.filter { it.category.equals(category, true) }.ifEmpty { productList }

            updateUiState(
                content.copy(
                    productList = filteredList,
                    selectedCategoryIndex = selectedCategoryIndex
                )
            )
        }
    }
}
