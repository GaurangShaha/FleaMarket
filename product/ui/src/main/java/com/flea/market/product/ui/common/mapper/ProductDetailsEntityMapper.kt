package com.flea.market.product.ui.common.mapper

import com.flea.market.product.data.remote.entity.ProductDetailsEntity
import com.flea.market.product.ui.list.CategoryListImmutableWrapper
import java.util.Locale

internal fun ProductDetailsEntity.toProductDetailsViewEntity() =
    com.flea.market.product.ui.common.entity.ProductDetailsViewEntity(
        category = category.replaceFirstChar {
            if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString()
        },
        description = description,
        id = id,
        imageList = imageList,
        price = price,
        count = rating.count,
        rate = rating.rate,
        title = title
    )

internal fun List<ProductDetailsEntity>.toCategoryListWrapper(): CategoryListImmutableWrapper {
    val categoryList = mutableListOf<String>()
    categoryList.add("All")

    categoryList.addAll(
        distinctBy { it.category }.map { productDetailsEntity ->
            productDetailsEntity.category.replaceFirstChar {
                if (it.isLowerCase()) {
                    it.titlecase(
                        Locale.getDefault()
                    )
                } else {
                    it.toString()
                }
            }
        }
    )

    return CategoryListImmutableWrapper(categoryList.toList())
}
