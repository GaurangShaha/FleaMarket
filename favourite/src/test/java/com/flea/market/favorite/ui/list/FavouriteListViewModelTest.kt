package com.flea.market.favorite.ui.list

import com.flea.market.cart.data.repository.CartRepository
import com.flea.market.favorite.ui.input.favouriteItemViewEntity
import com.flea.market.favorite.ui.input.favouriteProductDetailsEntityList
import com.flea.market.favorite.ui.list.FavouriteListIntent.MoveToCart
import com.flea.market.favorite.ui.list.FavouriteListIntent.RemoveFromFavourite
import com.flea.market.favorite.ui.list.FavouriteListIntent.SnackbarResult
import com.flea.market.favorite.ui.list.FavouriteListUiState.Content
import com.flea.market.favorite.ui.list.FavouriteListUiState.Empty
import com.flea.market.favorite.ui.list.FavouriteListUiState.Error
import com.flea.market.favorite.ui.list.mapper.toFavouriteItemViewEntity
import com.flea.market.favourite.repository.FavouriteRepository
import com.flea.market.foundation.model.InternetDisconnectionException
import com.flea.market.foundation.model.Result.Success
import com.flea.market.product.test.MainThreadTestListener
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest

internal class FavouriteListViewModelTest : BehaviorSpec({
    listeners(MainThreadTestListener())

    Given("FavouriteListViewModel is created") {
        val favouriteRepositoryMock = mockk<FavouriteRepository>()
        val cartRepositoryMock = mockk<CartRepository>()

        When("favourite products stream return empty list") {
            every { favouriteRepositoryMock.getFavouriteProductsStream() } returns flowOf(
                emptyList()
            )

            val favouriteListViewModel =
                FavouriteListViewModel(favouriteRepositoryMock, cartRepositoryMock)

            // Test case works fine only if this line is present, need to find proper solution for it
            runTest { }

            Then("uiState should be Empty") {
                favouriteListViewModel.uiState.value shouldBe Empty
            }
        }

        When("favourite products stream throws exception") {
            every { favouriteRepositoryMock.getFavouriteProductsStream() } returns flow {
                throw InternetDisconnectionException
            }

            val favouriteListViewModel =
                FavouriteListViewModel(favouriteRepositoryMock, cartRepositoryMock)

            // Test case works fine only if this line is present, need to find proper solution for it
            runTest { }

            Then("uiState should be Error") {
                favouriteListViewModel.uiState.value.shouldBeInstanceOf<Error>()
            }
        }

        When("favourite products stream returns non empty list") {
            every { favouriteRepositoryMock.getFavouriteProductsStream() } returns flowOf(
                favouriteProductDetailsEntityList
            )

            val favouriteListViewModel =
                FavouriteListViewModel(favouriteRepositoryMock, cartRepositoryMock)

            // Test case works fine only if this line is present, need to find proper solution for it
            runTest { }

            Then("uiState should have favourite product list") {
                val favouriteList = favouriteProductDetailsEntityList.toFavouriteItemViewEntity()

                val content = favouriteListViewModel.uiState.value as Content
                content.favouriteProductList shouldBe favouriteList
            }

            Then("uiState should not have snackbarDetails") {
                val content = favouriteListViewModel.uiState.value as Content
                content.snackbarDetails shouldBe null
            }

            And("processIntent called with MoveToCart") {
                coEvery { cartRepositoryMock.addOrUpdateItem(any()) } returns Success(Unit)
                coEvery { favouriteRepositoryMock.removeFromFavourite(any()) } returns Success(Unit)

                favouriteListViewModel.processIntent(MoveToCart(favouriteItemViewEntity))

                Then("uiState should have snackbarDetails") {
                    val content = favouriteListViewModel.uiState.value as Content
                    content.snackbarDetails.shouldNotBeNull()
                }

                And("processIntent called with SnackbarResult") {
                    favouriteListViewModel.processIntent(SnackbarResult(false))

                    Then("uiState should not have snackbarDetails") {
                        val content = favouriteListViewModel.uiState.value as Content
                        content.snackbarDetails shouldBe null
                    }
                }
            }

            And("processIntent called with RemoveFromFavourite") {
                coEvery { favouriteRepositoryMock.removeFromFavourite(any()) } returns Success(Unit)
                favouriteListViewModel.processIntent(RemoveFromFavourite(1))

                Then("favourite repository's removeFromFavourite function should be called") {
                    coVerify { favouriteRepositoryMock.removeFromFavourite(eq(1)) }
                }
            }
        }
    }
})
