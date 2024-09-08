package com.flea.market.favorite.ui.list.mapper

import com.flea.market.favorite.ui.input.favouriteItemViewEntity
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

internal class FavouriteItemViewEntityMapperTest : BehaviorSpec({
    Given("FavouriteItemViewEntity.toCartProductDetails() is called") {
        val result = favouriteItemViewEntity.toCartProductDetails()

        Then("should return CartProductDetailsEntity") {
            result.id shouldBe favouriteItemViewEntity.id
            result.category shouldBe favouriteItemViewEntity.category
            result.image shouldBe favouriteItemViewEntity.image
            result.price shouldBe favouriteItemViewEntity.price
            result.title shouldBe favouriteItemViewEntity.title
            result.quantity shouldBe 1
        }
    }
})
