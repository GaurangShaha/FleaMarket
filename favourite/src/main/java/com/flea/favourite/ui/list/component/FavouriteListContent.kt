package com.flea.favourite.ui.list.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.flea.common.ui.component.preview.FleaMarketPreview
import com.flea.common.ui.component.preview.FleaMarketThemePreview
import com.flea.favourite.ui.list.FavouriteListIntent
import com.flea.favourite.ui.list.FavouriteListIntent.RemoveFromFavourite
import com.flea.favourite.ui.list.FavouriteListUiState

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun FavouriteListContent(
    uiState: FavouriteListUiState.Content,
    handleIntent: (FavouriteListIntent) -> Unit,
    navigateToProductDetails: (productId: Int) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(156.dp),
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(bottom = 90.dp, start = 16.dp, end = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(items = uiState.favouriteProductList, key = { it.id }) { favouriteItemViewEntity ->
            FavouriteProductItem(
                modifier = Modifier.animateItemPlacement(),
                favouriteItem = favouriteItemViewEntity,
                navigateToProductDetails = navigateToProductDetails,
                removeFromFavourite = { handleIntent(RemoveFromFavourite(it)) },
                moveToCart = { handleIntent(FavouriteListIntent.MoveToCart(it)) }
            )
        }
    }
}

@FleaMarketPreview
@Composable
private fun FavouriteListContentPreview() {
    FleaMarketThemePreview {
        FavouriteListContent(
            uiState = dummyContent,
            handleIntent = {},
            navigateToProductDetails = {}
        )
    }
}