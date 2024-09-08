package com.flea.market.cart.ui.details.mapper

import com.flea.market.cart.ui.details.input.cartProductDetailsEntityList
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

internal class CartProductDetailsMapperTest : BehaviorSpec({
    Given("cartProductDetailsEntity.toItemsInCartViewEntity() is called") {
        Then("should return ItemsInCartViewEntity") {
            with(cartProductDetailsEntityList.first().toItemsInCartViewEntity()) {
                category shouldBe "Men's clothing"
                id shouldBe 1
                image shouldBe "https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg"
                price shouldBe 109.95
                quantity shouldBe 1
                title shouldBe "Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops"
            }
        }
    }
})
