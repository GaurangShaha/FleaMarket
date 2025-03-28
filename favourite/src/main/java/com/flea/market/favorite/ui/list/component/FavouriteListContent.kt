package com.flea.market.favorite.ui.list.component

import android.artisan.ui.component.FleaMarketSnackbarHost
import android.artisan.ui.component.snackbar.SnackbarDelegate
import android.artisan.ui.compositionlocal.LocalWindowSizeClass
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.SnackbarResult.ActionPerformed
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.flea.market.favorite.ui.list.FavouriteListIntent
import com.flea.market.favorite.ui.list.FavouriteListIntent.MoveToCart
import com.flea.market.favorite.ui.list.FavouriteListIntent.RemoveFromFavourite
import com.flea.market.favorite.ui.list.FavouriteListIntent.SnackbarResult
import com.flea.market.favorite.ui.list.FavouriteListUiState

@Composable
internal fun FavouriteListContent(
    uiState: FavouriteListUiState.Content,
    processIntent: (FavouriteListIntent) -> Unit
) {
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(snackbarHost = {
        val bottomPadding =
            if (LocalWindowSizeClass.current.widthSizeClass == WindowWidthSizeClass.Compact) {
                72.dp
            } else {
                0.dp
            }

        FleaMarketSnackbarHost(
            snackbarHostState = snackbarHostState,
            modifier = Modifier.padding(bottom = bottomPadding)
        )
    }) { paddingValues ->
        LazyVerticalGrid(
            columns = GridCells.Adaptive(156.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(bottom = 90.dp, start = 16.dp, end = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(
                items = uiState.favouriteProductList,
                key = { it.id }
            ) { favouriteItemViewEntity ->
                FavouriteProductItem(
                    favouriteItem = favouriteItemViewEntity,
                    onRemoveFromFavourite = {
                        processIntent(RemoveFromFavourite(favouriteItemViewEntity.id))
                    },
                    onMoveToCart = {
                        processIntent(MoveToCart(favouriteItemViewEntity))
                    },
                    modifier = Modifier.animateItem()
                )
            }
        }

        uiState.snackbarDetails?.let {
            val message = stringResource(it.message)
            val action = stringResource(it.actionLabel)
            LaunchedEffect(it) {
                val result = SnackbarDelegate.showSnackbar(
                    snackbarHostState = snackbarHostState,
                    message = message,
                    action = action,
                    type = it.snackbarType
                )
                processIntent(SnackbarResult(result == ActionPerformed))
            }
        }
    }
}
