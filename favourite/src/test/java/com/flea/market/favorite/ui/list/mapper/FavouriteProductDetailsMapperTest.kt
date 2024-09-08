package com.flea.market.favorite.ui.list.mapper

import com.flea.market.favorite.ui.input.favouriteProductDetailsEntityList
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

internal class FavouriteProductDetailsMapperTest : BehaviorSpec({
    Given("List<FavouriteProductDetailsEntity>.toFavouriteItemViewEntity() is called") {
        val result = favouriteProductDetailsEntityList.toFavouriteItemViewEntity()

        Then("should return List<FavouriteItemViewEntity>") {
            result.forEachIndexed { index, favouriteItemViewEntity ->
                favouriteItemViewEntity.category shouldBe favouriteProductDetailsEntityList[index].category
                favouriteItemViewEntity.description shouldBe favouriteProductDetailsEntityList[index].description
                favouriteItemViewEntity.id shouldBe favouriteProductDetailsEntityList[index].id
                favouriteItemViewEntity.image shouldBe favouriteProductDetailsEntityList[index].image
                favouriteItemViewEntity.price shouldBe favouriteProductDetailsEntityList[index].price
                favouriteItemViewEntity.rating shouldBe favouriteProductDetailsEntityList[index].rating
                favouriteItemViewEntity.title shouldBe favouriteProductDetailsEntityList[index].title
            }
        }
    }
})
