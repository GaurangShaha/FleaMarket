package com.flea.market.product.ui.list.component

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.flea.market.product.ui.list.CategoryListImmutableWrapper
import com.flea.market.product.ui.list.ProductListIntent
import com.flea.market.ui.preview.FleaMarketPreviews
import com.flea.market.ui.preview.FleaMarketThemePreview
import com.flea.market.ui.theme.extraShape

private const val TEXT_COLOR_ANIMATION_TIME = 300

@Composable
internal fun CategorySection(
    categoryListWrapper: CategoryListImmutableWrapper,
    selectedCategoryIndex: Int,
    modifier: Modifier = Modifier,
    onHandleIntent: (ProductListIntent) -> Unit
) {
    LazyRow(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        itemsIndexed(
            items = categoryListWrapper.items,
            key = { _, category -> category }
        ) { index, category ->
            val bgColor by animateColorAsState(
                targetValue = getBackgroundColor(selectedCategoryIndex, index),
                animationSpec = tween(TEXT_COLOR_ANIMATION_TIME),
                label = "categoryBgColor"
            )

            val textColor by animateColorAsState(
                targetValue = getTextColor(selectedCategoryIndex, index),
                animationSpec = tween(TEXT_COLOR_ANIMATION_TIME),
                label = "categoryBgColor"
            )

            Text(
                modifier = Modifier
                    .clip(MaterialTheme.extraShape.capsuleShape)
                    .selectable(selectedCategoryIndex == index, onClick = {
                        onHandleIntent(ProductListIntent.FilterByCategory(index))
                    })
                    .background(bgColor)
                    .padding(
                        start = 16.dp,
                        end = 16.dp,
                        top = 4.dp,
                        bottom = 4.dp
                    ),
                text = category,
                color = textColor
            )
        }
    }
}

@Composable
private fun getTextColor(
    selectedCategoryIndex: Int,
    index: Int
) = if (selectedCategoryIndex == index) {
    MaterialTheme.colors.onPrimary
} else {
    MaterialTheme.colors.onSecondary
}

@Composable
private fun getBackgroundColor(
    selectedCategoryIndex: Int,
    index: Int
) = if (selectedCategoryIndex == index) {
    MaterialTheme.colors.primary
} else {
    MaterialTheme.colors.secondary
}

@FleaMarketPreviews
@Composable
internal fun CategorySectionPreview() {
    FleaMarketThemePreview {
        CategorySection(
            categoryListWrapper = dummyCategoryListWrapper,
            selectedCategoryIndex = 0
        ) {}
    }
}
