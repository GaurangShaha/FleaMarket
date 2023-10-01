package com.flea.market.favorite.ui.list

import com.flea.market.favorite.ui.list.entity.FavouriteItemViewEntity

internal sealed class FavouriteListIntent {
    class RemoveFromFavourite(val productId: Int) : FavouriteListIntent()
    class MoveToCart(val favouriteItemViewEntity: FavouriteItemViewEntity) : FavouriteListIntent()
}
