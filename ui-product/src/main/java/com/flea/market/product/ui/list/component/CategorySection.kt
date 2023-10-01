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
import com.flea.market.product.ui.list.ProductListIntent
import com.flea.market.ui.preview.FleaMarketPreview
import com.flea.market.ui.preview.FleaMarketThemePreview
import com.flea.market.ui.theme.extraShape

@Composable
internal fun CategorySection(
    modifier: Modifier = Modifier,
    categories: List<String>,
    selectedCategoryIndex: Int,
    handleIntent: (ProductListIntent) -> Unit
) {
    LazyRow(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        itemsIndexed(items = categories, key = { _, it -> it }) { index, it ->
            val bgColor by animateColorAsState(
                targetValue = if (selectedCategoryIndex == index) MaterialTheme.colors.primary else MaterialTheme.colors.secondary,
                animationSpec = tween(300),
                label = "categoryBgColor"
            )

            val textColor by animateColorAsState(
                targetValue = if (selectedCategoryIndex == index) MaterialTheme.colors.onPrimary else MaterialTheme.colors.onSecondary,
                animationSpec = tween(300),
                label = "categoryBgColor"
            )

            Text(
                modifier = Modifier
                    .clip(MaterialTheme.extraShape.capsuleShape)
                    .selectable(selectedCategoryIndex == index, onClick = {
                        handleIntent(ProductListIntent.FilterByCategory(index))
                    })
                    .background(bgColor)
                    .padding(
                        start = 16.dp, end = 16.dp, top = 4.dp, bottom = 4.dp
                    ), text = it, color = textColor
            )
        }
    }
}

@FleaMarketPreview
@Composable
private fun CategorySectionPreview() {
    FleaMarketThemePreview {
        CategorySection(
            categories = dummyCategoryList,
            selectedCategoryIndex = 0,
            handleIntent = {})
    }
}