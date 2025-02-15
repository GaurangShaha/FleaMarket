package com.flea.market.favorite.ui.list

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.flea.market.common.mapper.toAPIErrorIcon
import com.flea.market.common.mapper.toAPIErrorMessage
import com.flea.market.favorite.ui.list.FavouriteListUiState.Content
import com.flea.market.favorite.ui.list.FavouriteListUiState.Empty
import com.flea.market.favorite.ui.list.FavouriteListUiState.Error
import com.flea.market.favorite.ui.list.FavouriteListUiState.Loading
import com.flea.market.favorite.ui.list.component.FavouriteListContent
import com.flea.market.favorite.ui.list.component.FavouriteListLoading
import com.flea.market.favourite.ui.R
import com.flea.market.ui.component.EmptyLayout
import com.flea.market.ui.component.ErrorLayout
import com.flea.market.ui.component.FleaMarketAppBar

@Composable
internal fun FavouriteListScreen(
    uiState: FavouriteListUiState,
    processIntent: (FavouriteListIntent) -> Unit
) {
    Column {
        FleaMarketAppBar(navigationIcon = Icons.Default.Menu to {}, title = R.string.favourites)

        when (uiState) {
            Loading -> FavouriteListLoading()
            Empty -> EmptyLayout(
                message = stringResource(id = R.string.empty_favourite),
                icon = painterResource(id = R.drawable.ic_empty_favourite)
            )

            is Content -> {
                FavouriteListContent(uiState = uiState, processIntent = processIntent)
            }

            is Error -> ErrorLayout(
                errorMessage = stringResource(id = uiState.throwable.toAPIErrorMessage()),
                errorIcon = painterResource(id = uiState.throwable.toAPIErrorIcon())
            )
        }
    }
}
