package com.flea.market.product.ui.details

import android.artisan.foundation.model.Result.Failure
import android.artisan.foundation.model.Result.Success
import android.artisan.test.common.test.MainThreadTestListener
import android.artisan.ui.common.navigation.ProductDetailsDestination
import android.artisan.ui.component.ButtonState.Initial
import com.flea.market.cart.data.repository.CartRepository
import com.flea.market.favourite.repository.FavouriteRepository
import com.flea.market.product.data.repository.ProductRepository
import com.flea.market.product.ui.common.mapper.toProductDetailsViewEntity
import com.flea.market.product.ui.details.ProductDetailsIntent.AddToCart
import com.flea.market.product.ui.details.ProductDetailsIntent.Reload
import com.flea.market.product.ui.details.ProductDetailsIntent.ToggleMarkAsFavourite
import com.flea.market.product.ui.details.ProductDetailsUiState.Content
import com.flea.market.product.ui.details.ProductDetailsUiState.Error
import com.flea.market.product.ui.input.productDetailsEntityList
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeTypeOf
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest

internal class ProductDetailsViewModelTest : BehaviorSpec({
    listeners(MainThreadTestListener())

    Given("ProductDetailsViewModel is created") {
        And("product details fetched successfully") {
            val productDetailsViewEntity = productDetailsEntityList.first().toProductDetailsViewEntity()

            val productRepositoryMock = mockk<ProductRepository>()
            val cartRepositoryMock = mockk<CartRepository>()
            val favouriteRepositoryMock = mockk<FavouriteRepository>()

            coEvery { productRepositoryMock.getProductDetails(any()) } returns Success(
                productDetailsEntityList.first()
            )

            When("product is marked as favourite") {
                coEvery { favouriteRepositoryMock.isMarkedAsFavourite(any()) } returns Success(true)

                val productDetailsViewModel = ProductDetailsViewModel(
                    productDetailsDestination = ProductDetailsDestination(1),
                    productRepository = productRepositoryMock,
                    cartRepository = cartRepositoryMock,
                    favouriteRepository = favouriteRepositoryMock
                )

                Then("uiState should have product details") {
                    val content = productDetailsViewModel.uiState.value as Content
                    content.productDetails shouldBe productDetailsViewEntity
                }

                Then("uiState should have marked as favourite as true") {
                    val content = productDetailsViewModel.uiState.value as Content
                    content.markedAsFavourite shouldBe true
                }

                Then("uiState should have add to cart button state as Initial") {
                    val content = productDetailsViewModel.uiState.value as Content
                    content.addToCartButtonState shouldBe Initial
                }

                And("processIntent called with ToggleMarkAsFavourite with false") {
                    coEvery { favouriteRepositoryMock.removeFromFavourite(any()) } returns Success(
                        Unit
                    )

                    productDetailsViewModel.processIntent(ToggleMarkAsFavourite(false))

                    Then("favouriteRepository removeFromFavourite function should be called") {
                        coVerify { favouriteRepositoryMock.removeFromFavourite(any()) }
                    }

                    Then("uiState should have marked as favourite as false") {
                        val content = productDetailsViewModel.uiState.value as Content
                        content.markedAsFavourite shouldBe false
                    }
                }

                And("processIntent called with AddToCart") {
                    coEvery { cartRepositoryMock.addOrUpdateItem(any()) } returns Success(Unit)

                    productDetailsViewModel.processIntent(AddToCart)

                    // Test case works fine only if this line is present, need to find proper solution for it
                    runTest {}

                    Then("cartRepository addOrUpdateItem function should be called") {
                        coVerify { cartRepositoryMock.addOrUpdateItem(any()) }
                    }
                }
            }

            When("product is not marked as favourite") {
                coEvery { favouriteRepositoryMock.isMarkedAsFavourite(any()) } returns Success(false)

                val productDetailsViewModel = ProductDetailsViewModel(
                    productDetailsDestination = ProductDetailsDestination(1),
                    productRepository = productRepositoryMock,
                    cartRepository = cartRepositoryMock,
                    favouriteRepository = favouriteRepositoryMock
                )

                Then("uiState should have product details") {
                    val content = productDetailsViewModel.uiState.value as Content
                    content.productDetails shouldBe productDetailsViewEntity
                }

                Then("uiState should have marked as favourite as false") {
                    val content = productDetailsViewModel.uiState.value as Content
                    content.markedAsFavourite shouldBe false
                }

                Then("uiState should have add to cart button state as Initial") {
                    val content = productDetailsViewModel.uiState.value as Content
                    content.addToCartButtonState shouldBe Initial
                }

                And("processIntent called with ToggleMarkAsFavourite with true") {
                    coEvery { favouriteRepositoryMock.markAsFavourite(any()) } returns Success(Unit)

                    productDetailsViewModel.processIntent(ToggleMarkAsFavourite(true))

                    Then("favouriteRepository removeFromFavourite function should be called") {
                        coVerify { favouriteRepositoryMock.markAsFavourite(any()) }
                    }

                    Then("uiState should have marked as favourite as true") {
                        val content = productDetailsViewModel.uiState.value as Content
                        content.markedAsFavourite shouldBe true
                    }
                }
            }
        }

        And("failed to fetch product details") {
            val productRepositoryMock = mockk<ProductRepository>()
            val cartRepositoryMock = mockk<CartRepository>()
            val favouriteRepositoryMock = mockk<FavouriteRepository>()

            coEvery { productRepositoryMock.getProductDetails(any()) } returns Failure(Exception())

            val productDetailsViewModel = ProductDetailsViewModel(
                productDetailsDestination = ProductDetailsDestination(1),
                productRepository = productRepositoryMock,
                cartRepository = cartRepositoryMock,
                favouriteRepository = favouriteRepositoryMock
            )

            Then("uiState should have error") {
                productDetailsViewModel.uiState.value.shouldBeTypeOf<Error>()
            }

            When("processIntent called with Reload") {
                productDetailsViewModel.processIntent(Reload)

                Then("product repositories getProductDetails function should be called") {
                    coVerify(exactly = 2) { productRepositoryMock.getProductDetails(any()) }
                }
            }
        }
    }
})
