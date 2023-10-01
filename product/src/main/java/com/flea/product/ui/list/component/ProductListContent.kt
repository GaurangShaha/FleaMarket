package com.flea.product.ui.list.component

import androidx.compose.foundation.ExperimentalFoundationApi
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
import com.flea.common.ui.component.compositionlocal.LocalNavControllerProvider
import com.flea.common.ui.component.preview.FleaMarketPreview
import com.flea.common.ui.component.preview.FleaMarketThemePreview
import com.flea.product.ui.R
import com.flea.product.ui.details.navigation.navigateToProductDetails
import com.flea.product.ui.list.ProductListIntent
import com.flea.product.ui.list.ProductListUiState.Content

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun ProductListContent(
    state: Content,
    handleIntent: (ProductListIntent) -> Unit,
) {
    LazyVerticalStaggeredGrid(
        verticalItemSpacing = 22.dp,
        columns = StaggeredGridCells.Adaptive(150.dp),
        horizontalArrangement = Arrangement.spacedBy(22.dp),
        contentPadding = PaddingValues(start = 16.dp, end = 16.dp, bottom = 90.dp)
    ) {
        item(
            span = StaggeredGridItemSpan.Companion.FullLine,
            key = state.categoryList,
            contentType = "categories"
        ) {
            CategorySection(
                categories = state.categoryList,
                selectedCategoryIndex = state.selectedCategoryIndex,
                handleIntent = handleIntent
            )
        }

        item(contentType = "welcomeBanner") {
            Text(
                text = stringResource(R.string.welcome_message),
                style = MaterialTheme.typography.h4,

                )
        }

        items(items = state.productList, key = { it.id }, contentType = { "productList" }) {
            val navController = LocalNavControllerProvider.current
            ProductListItem(
                productDetails = it, onProductClick = navController::navigateToProductDetails
            )
        }
    }
}


@FleaMarketPreview
@Composable
private fun ProductListContentPreview() {
    FleaMarketThemePreview {
        ProductListContent(state = dummyProductListContent, handleIntent = {})
    }
}