package com.flea.favourite.ui.list

import com.flea.favourite.ui.list.entity.FavouriteItemViewEntity

internal sealed class FavouriteListIntent {
    class RemoveFromFavourite(val productId: Int) : FavouriteListIntent()
    class MoveToCart(val favouriteItemViewEntity: FavouriteItemViewEntity) : FavouriteListIntent()
}
