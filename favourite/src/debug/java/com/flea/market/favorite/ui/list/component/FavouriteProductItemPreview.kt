package com.flea.market.favorite.ui.list.component

import androidx.compose.runtime.Composable
import com.flea.market.favorite.ui.dummy.data.dummyFavouriteItemList
import com.flea.market.ui.preview.FleaMarketPreviews
import com.flea.market.ui.preview.FleaMarketThemePreview

@FleaMarketPreviews
@Composable
private fun FavouriteProductItemPreview() {
    FleaMarketThemePreview {
        FavouriteProductItem(
            favouriteItem = dummyFavouriteItemList.first(),
            onRemoveFromFavourite = {},
            onMoveToCart = {}
        )
    }
}
