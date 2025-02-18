package com.flea.market.cart.data.repository

import android.artisan.foundation.model.Result.Failure
import android.artisan.foundation.model.Result.Success
import com.flea.market.cart.data.input.cartProductDetailsEntityList
import com.flea.market.cart.data.local.entity.CartProductDetailsEntity
import com.flea.market.cart.data.local.source.CartLocalSource
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.types.shouldBeInstanceOf
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlin.coroutines.cancellation.CancellationException

internal class DefaultCartRepositoryTest : BehaviorSpec({
    val cartLocalSource = mockk<CartLocalSource>()
    val cartRepository: DefaultCartRepository = DefaultCartRepository(cartLocalSource)

    Given("cart repository's getItemsInCartStream function is called") {
        When("items in cart got fetched successfully") {
            coEvery { cartLocalSource.getItemsInCartStream() } returns flow { cartProductDetailsEntityList }

            Then("should return flow of list of CartProductDetailsEntity") {
                cartRepository.getItemsInCartStream()
                    .shouldBeInstanceOf<Flow<List<CartProductDetailsEntity>>>()
            }
        }

        When("items in cart fetching failed") {
            coEvery { cartLocalSource.getItemsInCartStream() } returns flow { throw CancellationException() }

            Then("should throw CancellationException") {
                shouldThrow<CancellationException> {
                    cartRepository.getItemsInCartStream().collect()
                }
            }
        }
    }

    Given("cart repository's addOrUpdateItem function is called") {
        When("item does not exist in cart") {
            coEvery { cartLocalSource.getProductById(any()) } returns null

            And("item added successfully") {
                coEvery { cartLocalSource.addOrUpdateProduct(any()) } returns Unit
                cartRepository.addOrUpdateItem(cartProductDetailsEntityList.first())

                Then("should call addOrUpdateProduct function of cartLocalSource with provided value") {
                    coVerify {
                        cartLocalSource.addOrUpdateProduct(
                            eq(cartProductDetailsEntityList.first())
                        )
                    }
                }
            }

            And("item adding failed") {
                coEvery { cartLocalSource.addOrUpdateProduct(any()) } throws Exception()
                val result =
                    cartRepository.addOrUpdateItem(cartProductDetailsEntityList.first())

                Then("should return failure") {
                    result.shouldBeInstanceOf<Failure>()
                }
            }
        }

        When("item already exists in cart") {
            coEvery { cartLocalSource.getProductById(any()) } returns cartProductDetailsEntityList.first()

            And("item updated successfully") {
                coEvery { cartLocalSource.addOrUpdateProduct(any()) } returns Unit
                cartRepository.addOrUpdateItem(cartProductDetailsEntityList.first())

                Then("should call addOrUpdateProduct function of cartLocalSource with incremented quantitt") {
                    coVerify {
                        cartLocalSource.addOrUpdateProduct(
                            eq(cartProductDetailsEntityList.first().copy(quantity = 2))
                        )
                    }
                }
            }

            And("item adding failed") {
                coEvery { cartLocalSource.addOrUpdateProduct(any()) } throws Exception()
                val result =
                    cartRepository.addOrUpdateItem(cartProductDetailsEntityList.first())

                Then("should return failure") {
                    result.shouldBeInstanceOf<Failure>()
                }
            }
        }
    }

    Given("cart repository's removeItem function is called") {
        When("product got removed successfully") {
            coEvery { cartLocalSource.removeProduct(any()) } returns Unit
            val result = cartRepository.removeItem(1)

            Then("should return success") {
                result.shouldBeInstanceOf<Success<Unit>>()
            }
        }

        When("product did not got removed") {
            coEvery { cartLocalSource.removeProduct(any()) } throws Exception()
            val result = cartRepository.removeItem(1)

            Then("should return failure") {
                result.shouldBeInstanceOf<Failure>()
            }
        }
    }

    Given("cart repository's updateQuantity function is called") {
        When("quantity updated successfully") {
            coEvery { cartLocalSource.addOrUpdateProduct(any()) } returns Unit
            val result = cartRepository.updateQuantity(1, 2)

            Then("should return success") {
                result.shouldBeInstanceOf<Success<Unit>>()
            }
        }

        When("quantity updating failed") {
            coEvery { cartLocalSource.addOrUpdateProduct(any()) } throws Exception()
            val result = cartRepository.updateQuantity(1, 2)

            Then("should return failure") {
                result.shouldBeInstanceOf<Failure>()
            }
        }
    }
})
