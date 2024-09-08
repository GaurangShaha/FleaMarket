package com.flea.market.product.ui.common.mapper

import com.flea.market.product.ui.input.productDetailsEntityList
import com.flea.market.product.ui.input.productDetailsViewEntity
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

@Suppress("MaxLineLength")
internal class ProductDetailsEntityMapperTest : BehaviorSpec({
    Given("ProductDetailsEntity.toProductDetailsViewEntity() is called") {
        Then("should return ProductDetailsViewEntity") {
            productDetailsEntityList.first().toProductDetailsViewEntity() shouldBe productDetailsViewEntity
        }
    }

    Given("ProductDetailsEntity.toCategoryListWrapper() is called") {
        Then("should return CategoryListImmutableWrapper") {
            val categoryList =
                listOf("All", "Men's clothing", "Jewelery", "Electronics", "Women's clothing")
            productDetailsEntityList.toCategoryListWrapper().items shouldBe categoryList
        }
    }
})
