package com.flea.market.cart.ui.details

import com.flea.market.cart.data.local.entity.CartProductDetailsEntity
import com.flea.market.cart.data.repository.CartRepository
import com.flea.market.cart.ui.details.CartDetailsIntent.DecreaseQuantity
import com.flea.market.cart.ui.details.CartDetailsIntent.IncreaseQuantity
import com.flea.market.cart.ui.details.CartDetailsIntent.RemoveFromCart
import com.flea.market.cart.ui.details.CartDetailsUiState.Empty
import com.flea.market.cart.ui.details.CartDetailsUiState.Error
import com.flea.market.cart.ui.details.entity.PriceDetailsViewEntity
import com.flea.market.cart.ui.details.input.cartProductDetailsEntityList
import com.flea.market.cart.ui.details.input.itemsInCartViewEntity
import com.flea.market.cart.ui.details.mapper.toItemsInCartViewEntity
import com.flea.market.foundation.model.InternetConnectionException
import com.flea.market.foundation.model.Result.Success
import com.flea.market.product.test.MainThreadTestListener
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeTypeOf
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest

@OptIn(ExperimentalCoroutinesApi::class)
internal class CartDetailsViewModelTest : BehaviorSpec({
    listeners(MainThreadTestListener())

    Given("CartDetailsViewModel is created") {
        val cartRepository = mockk<CartRepository>()

        When("Cart item stream has the empty list") {
            coEvery { cartRepository.getItemsInCartStream() } returns flowOf(emptyList())

            val cartDetailsViewModel = CartDetailsViewModel(cartRepository)

            runTest {
                backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
                    cartDetailsViewModel.uiState.collect {}
                }
            }

            Then("uiState should be Empty") {
                cartDetailsViewModel.uiState.value shouldBe Empty
            }
        }

        When("Cart item stream throws exception") {
            val itemInCartFlow = flow<List<CartProductDetailsEntity>> {
                throw InternetConnectionException
            }
            coEvery { cartRepository.getItemsInCartStream() } returns itemInCartFlow

            val cartDetailsViewModel = CartDetailsViewModel(cartRepository)

            runTest {
                backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
                    cartDetailsViewModel.uiState.collect {}
                }
            }

            Then("uiState should be Error") {
                cartDetailsViewModel.uiState.value.shouldBeTypeOf<Error>()
            }
        }

        When("Cart item stream has the non empty list") {
            coEvery { cartRepository.getItemsInCartStream() } returns flowOf(
                cartProductDetailsEntityList
            )
            val cartDetailsViewModel = CartDetailsViewModel(cartRepository)

            runTest {
                backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
                    cartDetailsViewModel.uiState.collect()
                }
            }

            Then("uiState should have productList") {
                val result = cartProductDetailsEntityList.map { it.toItemsInCartViewEntity() }

                val content = cartDetailsViewModel.uiState.value as CartDetailsUiState.Content
                content.productList shouldBe result
            }

            Then("uiState should have priceDetails") {
                val result = PriceDetailsViewEntity(
                    subTotal = 109.95,
                    discount = 10.995000000000001,
                    tax = 9.8955,
                    totalPayable = 108.8505
                )

                val content = cartDetailsViewModel.uiState.value as CartDetailsUiState.Content
                content.priceDetails shouldBe result
            }

            And("onHandleIntent is called with IncreaseQuantity") {
                coEvery { cartRepository.updateQuantity(any(), any()) } returns Success(Unit)
                cartDetailsViewModel.onHandleIntent(IncreaseQuantity(itemsInCartViewEntity))

                Then("cart repository's updateQuantity function should be called") {
                    coVerify {
                        cartRepository.updateQuantity(
                            eq(itemsInCartViewEntity.id),
                            eq(itemsInCartViewEntity.quantity + 1)
                        )
                    }
                }
            }

            And("onHandleIntent is called with DecreaseQuantity") {
                And("quantity of item in cart is 1") {
                    coEvery { cartRepository.removeItem(any()) } returns Success(Unit)
                    cartDetailsViewModel.onHandleIntent(DecreaseQuantity(itemsInCartViewEntity))

                    Then("cart repository's removeItem function should be called") {
                        coVerify {
                            cartRepository.removeItem(eq(itemsInCartViewEntity.id))
                        }
                    }
                }

                And("quantity of item in cart is greater than 1") {
                    coEvery { cartRepository.updateQuantity(any(), any()) } returns Success(Unit)
                    cartDetailsViewModel.onHandleIntent(
                        DecreaseQuantity(itemsInCartViewEntity.copy(quantity = 2))
                    )

                    Then("cart repository's updateQuantity function should be called") {
                        coVerify {
                            cartRepository.updateQuantity(
                                eq(itemsInCartViewEntity.id),
                                eq(1)
                            )
                        }
                    }
                }
            }

            And("onHandleIntent is called with RemoveFromCart") {
                coEvery { cartRepository.removeItem(any()) } returns Success(Unit)
                cartDetailsViewModel.onHandleIntent(RemoveFromCart(itemsInCartViewEntity))

                Then("cart repository's removeItem function should be called") {
                    coVerify {
                        cartRepository.removeItem(eq(itemsInCartViewEntity.id))
                    }
                }
            }
        }
    }
})
