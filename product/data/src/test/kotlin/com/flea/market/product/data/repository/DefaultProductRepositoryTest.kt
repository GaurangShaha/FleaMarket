package com.flea.market.product.data.repository

import android.artisan.foundation.extension.getOrThrow
import android.artisan.foundation.model.Result.Failure
import android.artisan.foundation.model.Result.Success
import com.flea.market.product.data.input.productDetailsEntityList
import com.flea.market.product.data.remote.entity.ProductDetailsEntity
import com.flea.market.product.data.remote.source.ProductRemoteSource
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeTypeOf
import io.mockk.coEvery
import io.mockk.mockk

class DefaultProductRepositoryTest : BehaviorSpec({
    val productRemoteSource = mockk<ProductRemoteSource>()
    val productRepository: ProductRepository = DefaultProductRepository(productRemoteSource)

    Given("product repository's getProductList function is called") {
        When("product list got fetched successfully") {
            coEvery { productRemoteSource.getProductList() } returns Success(
                productDetailsEntityList
            )
            val result = productRepository.getProductList()

            Then("should return success") {
                result.shouldBeTypeOf<Success<List<ProductDetailsEntity>>>()
            }

            Then("should return product list") {
                result.getOrThrow().mapIndexed { index, productDetailsEntity ->
                    productDetailsEntity.id shouldBe productDetailsEntityList[index].id
                    productDetailsEntity.title shouldBe productDetailsEntityList[index].title
                    productDetailsEntity.price shouldBe productDetailsEntityList[index].price
                    productDetailsEntity.description shouldBe productDetailsEntityList[index].description
                    productDetailsEntity.category shouldBe productDetailsEntityList[index].category
                    productDetailsEntity.image shouldBe productDetailsEntityList[index].image
                    productDetailsEntity.rating shouldBe productDetailsEntityList[index].rating
                }
            }
        }

        When("product list fetching failed") {
            coEvery { productRemoteSource.getProductList() } returns Failure(Exception())
            val result = productRepository.getProductList()

            Then("should return failure") {
                result.shouldBeTypeOf<Failure>()
            }
        }
    }

    Given("product repository's getProductDetails function is called") {
        When("product details got fetched successfully") {
            coEvery { productRemoteSource.getProductDetails(any()) } returns Success(
                productDetailsEntityList.first()
            )
            val result = productRepository.getProductDetails(1)

            Then("should return success") {
                result.shouldBeTypeOf<Success<ProductDetailsEntity>>()
            }

            Then("should return product details") {
                with(result.getOrThrow()) {
                    id shouldBe productDetailsEntityList.first().id
                    title shouldBe productDetailsEntityList.first().title
                    price shouldBe productDetailsEntityList.first().price
                    description shouldBe productDetailsEntityList.first().description
                    category shouldBe productDetailsEntityList.first().category
                    image shouldBe productDetailsEntityList.first().image
                    rating shouldBe productDetailsEntityList.first().rating
                }
            }
        }

        When("product details fetching failed") {
            coEvery { productRemoteSource.getProductDetails(any()) } returns Failure(Exception())
            val result = productRepository.getProductDetails(1)

            Then("should return failure") {
                result.shouldBeTypeOf<Failure>()
            }
        }
    }
})
