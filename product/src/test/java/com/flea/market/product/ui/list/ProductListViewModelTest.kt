package com.flea.market.product.ui.list

import com.flea.market.foundation.model.Result.Failure
import com.flea.market.foundation.model.Result.Success
import com.flea.market.product.data.repository.ProductRepository
import com.flea.market.product.test.MainThreadTestListener
import com.flea.market.product.ui.common.mapper.toCategoryListWrapper
import com.flea.market.product.ui.common.mapper.toProductDetailsViewEntity
import com.flea.market.product.ui.input.productDetailsEntityList
import com.flea.market.product.ui.list.ProductListIntent.FilterByCategory
import com.flea.market.product.ui.list.ProductListIntent.Reload
import com.flea.market.product.ui.list.ProductListUiState.Content
import com.flea.market.product.ui.list.ProductListUiState.Error
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk

class ProductListViewModelTest : BehaviorSpec({
    listeners(MainThreadTestListener())

    Given("ProductListViewModel created") {
        And("Product list was fetched successfully") {
            val productList = productDetailsEntityList.map { it.toProductDetailsViewEntity() }

            val productRepositoryMock = mockk<ProductRepository>()
            coEvery { productRepositoryMock.getProductList() } returns Success(productDetailsEntityList)

            val productListViewModel = ProductListViewModel(productRepositoryMock)

            Then("uiState should have product list") {
                val content = productListViewModel.uiState.value as Content
                content.productList shouldBe productList
            }

            Then("uiState should have category list") {
                val content = productListViewModel.uiState.value as Content
                val categoryListWrapper = productDetailsEntityList.toCategoryListWrapper()
                content.categoryListWrapper shouldBe categoryListWrapper
            }

            Then("uiState should selectedCategoryIndex as 0") {
                val content = productListViewModel.uiState.value as Content
                content.selectedCategoryIndex shouldBe 0
            }

            When("processIntent called with FilterByCategory with index 1") {
                productListViewModel.processIntent(FilterByCategory(1))

                Then("uiState should have filtered product list by men's clothing category") {
                    val content = productListViewModel.uiState.value as Content
                    content.productList shouldBe listOf(
                        productDetailsEntityList.first().toProductDetailsViewEntity()
                    )
                }
            }

            When("processIntent called with FilterByCategory with index 0") {
                productListViewModel.processIntent(FilterByCategory(0))

                Then("uiState should have original filter list") {
                    val content = productListViewModel.uiState.value as Content
                    content.productList shouldBe productList
                }
            }
        }

        And("Product list failed to fetch") {
            val productRepositoryMock = mockk<ProductRepository>()
            coEvery { productRepositoryMock.getProductList() } returns Failure(Exception())

            val productRepository = ProductListViewModel(productRepositoryMock)

            Then("uiState should be of type Error") {
                productRepository.uiState.value.shouldBeInstanceOf<Error>()
            }

            When("processIntent called with Reload") {
                productRepository.processIntent(Reload)

                Then("Product repositories getProductList function should be called") {
                    coVerify(exactly = 2) { productRepositoryMock.getProductList() }
                }
            }
        }
    }
})
