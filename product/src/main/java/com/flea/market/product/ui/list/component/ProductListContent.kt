package com.flea.market.product.ui.list.component

import android.artisan.ui.common.navigation.ProductDetailsDestination
import android.artisan.ui.compositionlocal.LocalNavController
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridItemSpan
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.flea.market.product.ui.R
import com.flea.market.product.ui.list.ProductListIntent
import com.flea.market.product.ui.list.ProductListUiState.Content

@Composable
internal fun ProductListContent(
    state: Content,
    processIntent: (ProductListIntent) -> Unit,
) {
    LazyVerticalStaggeredGrid(
        verticalItemSpacing = 22.dp,
        columns = StaggeredGridCells.Adaptive(150.dp),
        horizontalArrangement = Arrangement.spacedBy(22.dp),
        contentPadding = PaddingValues(start = 16.dp, end = 16.dp, bottom = 90.dp)
    ) {
        item(
            span = StaggeredGridItemSpan.Companion.FullLine,
            key = state.categoryListWrapper.items.toString(),
            contentType = "categories"
        ) {
            CategorySection(
                categoryListWrapper = state.categoryListWrapper,
                selectedCategoryIndex = state.selectedCategoryIndex,
                processIntent = processIntent
            )
        }

        item(contentType = "welcomeBanner", key = "welcome") {
            Text(
                text = stringResource(R.string.welcome_message),
                style = MaterialTheme.typography.h4
            )
        }

        items(items = state.productList, key = { it.id }, contentType = { "productList" }) {
            val navController = LocalNavController.current
            ProductListItem(productDetails = it, onProductClick = {
                navController.navigate(ProductDetailsDestination(it.id))
            })
        }
    }
}
