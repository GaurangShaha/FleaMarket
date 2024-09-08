package com.flea.market.favorite.ui.list.component

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
import com.flea.market.favorite.ui.list.FavouriteListIntent
import com.flea.market.favorite.ui.list.FavouriteListIntent.RemoveFromFavourite
import com.flea.market.favorite.ui.list.FavouriteListUiState
import com.flea.market.ui.preview.FleaMarketPreviews
import com.flea.market.ui.preview.FleaMarketThemePreview

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun FavouriteListContent(
    uiState: FavouriteListUiState.Content,
    onHandleIntent: (FavouriteListIntent) -> Unit
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
                favouriteItem = favouriteItemViewEntity,
                onRemoveFromFavourite = {
                    onHandleIntent(RemoveFromFavourite(favouriteItemViewEntity.id))
                },
                onMoveToCart = {
                    onHandleIntent(FavouriteListIntent.MoveToCart(favouriteItemViewEntity))
                },
                modifier = Modifier.animateItem()
            )
        }
    }
}

@FleaMarketPreviews
@Composable
private fun FavouriteListContentPreview() {
    FleaMarketThemePreview {
        FavouriteListContent(uiState = dummyContent, onHandleIntent = {})
    }
}
