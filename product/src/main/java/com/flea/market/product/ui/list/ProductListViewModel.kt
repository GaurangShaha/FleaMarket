package com.flea.market.product.ui.list

import android.artisan.foundation.extension.fold
import android.artisan.ui.common.contract.viewmodel.ViewModelContract
import android.artisan.ui.common.extension.ifInstanceOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.flea.market.product.data.remote.entity.ProductDetailsEntity
import com.flea.market.product.data.repository.ProductRepository
import com.flea.market.product.ui.common.entity.ProductDetailsViewEntity
import com.flea.market.product.ui.common.mapper.toCategoryListWrapper
import com.flea.market.product.ui.common.mapper.toProductDetailsViewEntity
import com.flea.market.product.ui.list.ProductListIntent.FilterByCategory
import com.flea.market.product.ui.list.ProductListIntent.Reload
import com.flea.market.product.ui.list.ProductListUiState.Content
import com.flea.market.product.ui.list.ProductListUiState.Error
import com.flea.market.product.ui.list.ProductListUiState.Loading
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

internal class ProductListViewModel(private val productRepository: ProductRepository) :
    ViewModelContract<ProductListUiState, ProductListIntent>, ViewModel() {
    private val _uiState: MutableStateFlow<ProductListUiState> = MutableStateFlow(Loading)
    override val uiState: StateFlow<ProductListUiState> = _uiState.asStateFlow()

    private lateinit var productList: List<ProductDetailsViewEntity>

    init {
        viewModelScope.launch {
            getProductList()
        }
    }

    override fun processIntent(intent: ProductListIntent) {
        when (intent) {
            Reload -> viewModelScope.launch { getProductList() }
            is FilterByCategory -> filterByCategory(intent.selectedCategoryIndex)
        }
    }

    private suspend fun getProductList() {
        _uiState.value = Loading
        productRepository.getProductList()
            .fold(::handleProductListSuccess, ::handleProductListFailure)
    }

    private fun filterByCategory(selectedCategoryIndex: Int) {
        uiState.ifInstanceOf<Content> { content ->
            val category = content.categoryListWrapper.items[selectedCategoryIndex]
            val filteredList =
                productList.filter { it.category.equals(category, true) }.ifEmpty { productList }

            _uiState.value = content.copy(
                productList = filteredList, selectedCategoryIndex = selectedCategoryIndex
            )
        }
    }

    private fun handleProductListFailure(throwable: Throwable) {
        _uiState.value = Error(throwable)
    }

    private fun handleProductListSuccess(productDetailsEntities: List<ProductDetailsEntity>) {
        productList = productDetailsEntities.map { it.toProductDetailsViewEntity() }
        _uiState.value = Content(
            productList = productList,
            categoryListWrapper = productDetailsEntities.toCategoryListWrapper(),
            selectedCategoryIndex = 0
        )
    }
}
