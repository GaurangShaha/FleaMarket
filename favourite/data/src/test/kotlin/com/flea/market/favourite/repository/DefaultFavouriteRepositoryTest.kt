package com.flea.market.favourite.repository

import com.flea.market.favourite.local.source.FavouriteLocalSource
import com.flea.market.favourite.repository.input.favouriteProductDetailsEntityList
import com.flea.market.foundation.extension.getOrThrow
import com.flea.market.foundation.model.Result.Failure
import com.flea.market.foundation.model.Result.Success
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf

internal class DefaultFavouriteRepositoryTest : BehaviorSpec({
    val favouriteLocalSource = mockk<FavouriteLocalSource>()
    val favouriteRepository: FavouriteRepository = DefaultFavouriteRepository(favouriteLocalSource)

    Given("Favourite repository's markAsFavourite function is called") {
        When("Favourite product is added successfully") {
            coEvery { favouriteLocalSource.addFavouriteProduct(any()) } just Runs
            val result =
                favouriteRepository.markAsFavourite(favouriteProductDetailsEntityList.first())

            Then("should return success") {
                result.shouldBeInstanceOf<Success<Unit>>()
            }
        }

        When("Favourite product is not added successfully") {
            coEvery { favouriteLocalSource.addFavouriteProduct(any()) } throws Exception()
            val result =
                favouriteRepository.markAsFavourite(favouriteProductDetailsEntityList.first())

            Then("should return failure") {
                result.shouldBeInstanceOf<Failure>()
            }
        }
    }

    Given("Favourite repository's removeFromFavourite function is called") {
        When("Favourite product is removed successfully") {
            coEvery { favouriteLocalSource.removeFavouriteProduct(any()) } just Runs
            val result = favouriteRepository.removeFromFavourite(1)

            Then("should return success") {
                result.shouldBeInstanceOf<Success<Unit>>()
            }
        }

        When("Favourite product is not removed successfully") {
            coEvery { favouriteLocalSource.removeFavouriteProduct(any()) } throws Exception()
            val result = favouriteRepository.removeFromFavourite(1)

            Then("should return failure") {
                result.shouldBeInstanceOf<Failure>()
            }
        }
    }

    Given("Favourite repository's isMarkedAsFavourite function is called") {
        When("Favourite list has the product") {
            coEvery { favouriteLocalSource.getFavouriteProductById(any()) }.returns(
                favouriteProductDetailsEntityList.first()
            )
            val result = favouriteRepository.isMarkedAsFavourite(1)

            Then("should return success with true") {
                result.getOrThrow().shouldBeTrue()
            }
        }

        When("Favourite list does not have the product") {
            coEvery { favouriteLocalSource.getFavouriteProductById(any()) } returns null
            val result = favouriteRepository.isMarkedAsFavourite(1)

            Then("should return success with false") {
                result.getOrThrow().shouldBeFalse()
            }
        }

        When("Find in favourite list throws exception") {
            coEvery { favouriteLocalSource.getFavouriteProductById(any()) } throws Exception()
            val result = favouriteRepository.isMarkedAsFavourite(1)

            Then("should return failure") {
                result.shouldBeInstanceOf<Failure>()
            }
        }
    }

    Given("Favourite repository's getFavouriteProductsStream function is called") {
        When("Favourite products stream throws exception") {
            every { favouriteLocalSource.getFavouriteProductsStream() } throws Exception()

            Then("should throw exception") {
                shouldThrow<Exception> {
                    favouriteRepository.getFavouriteProductsStream().collect()
                }
            }
        }

        When("Favourite products stream return list of favourite products") {
            every { favouriteLocalSource.getFavouriteProductsStream() } returns flowOf(
                favouriteProductDetailsEntityList
            )

            Then("should return list of favourite products") {
                favouriteRepository.getFavouriteProductsStream().collect {
                    it shouldBe favouriteProductDetailsEntityList
                }
            }
        }
    }
})
