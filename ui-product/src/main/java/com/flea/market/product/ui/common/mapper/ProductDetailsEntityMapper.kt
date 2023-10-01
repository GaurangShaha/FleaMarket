package com.flea.market.product.ui.common.mapper

import com.flea.market.data.product.remote.entity.ProductDetailsEntity
import com.flea.market.product.ui.common.entity.ProductDetailsViewEntity
import java.util.Locale

internal fun ProductDetailsEntity.toProductDetailsViewEntity() =
    com.flea.market.product.ui.common.entity.ProductDetailsViewEntity(
        category = category.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() },
        description = description,
        id = id,
        imageList = imageList,
        price = price,
        count = rating.count,
        rate = rating.rate,
        title = title
    )

internal fun List<ProductDetailsEntity>.toCategoryList(): List<String> {
    val categoryList = mutableListOf<String>()
    categoryList.add("All")

    categoryList.addAll(distinctBy { it.category }.map {
        it.category.replaceFirstChar {
            if (it.isLowerCase()) it.titlecase(
                Locale.getDefault()
            ) else it.toString()
        }
    })

    return categoryList
}
