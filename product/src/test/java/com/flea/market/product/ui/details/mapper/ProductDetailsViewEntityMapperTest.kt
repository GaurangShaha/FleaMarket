package com.flea.market.product.ui.details.mapper

import com.flea.market.product.ui.input.productDetailsViewEntity
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

internal class ProductDetailsViewEntityMapperTest : BehaviorSpec({
    Given("ProductDetailsViewEntity.toCartProductDetails() is called") {
        Then("should return CartProductDetailsEntity") {
            with(productDetailsViewEntity.toCartProductDetails()) {
                category shouldBe "Men's clothing"
                id shouldBe 1
                image shouldBe "https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg"
                price shouldBe 109.95
                quantity shouldBe 1
                title shouldBe "Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops"
            }
        }
    }

    @Suppress("MaxLineLength")
    Given("ProductDetailsViewEntity.toFavouriteProductDetails() is called") {
        Then("should return FavouriteProductDetailsEntity") {
            with(productDetailsViewEntity.toFavouriteProductDetails()) {
                category shouldBe "Men's clothing"
                id shouldBe 1
                image shouldBe "https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg"
                price shouldBe 109.95
                rating shouldBe 3.9
                title shouldBe "Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops"
                description shouldBe "Your perfect pack for everyday use and walks in the forest. Stash your laptop (up to 15 inches) in the padded sleeve, your everyday"
            }
        }
    }
})
