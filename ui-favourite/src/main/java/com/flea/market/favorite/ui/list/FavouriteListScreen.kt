package com.flea.market.favorite.ui.list

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.flea.market.common.mapper.toAPIErrorIcon
import com.flea.market.common.mapper.toAPIErrorMessage
import com.flea.market.favorite.ui.list.FavouriteListIntent.SnackbarResult
import com.flea.market.favorite.ui.list.FavouriteListUiState.Content
import com.flea.market.favorite.ui.list.FavouriteListUiState.Empty
import com.flea.market.favorite.ui.list.FavouriteListUiState.Error
import com.flea.market.favorite.ui.list.FavouriteListUiState.Loading
import com.flea.market.favorite.ui.list.component.FavouriteListContent
import com.flea.market.favorite.ui.list.component.ProductListLoading
import com.flea.market.favorite.ui.list.component.dummyContent
import com.flea.market.foundation.model.NetworkException
import com.flea.market.ui.component.EmptyLayout
import com.flea.market.ui.component.ErrorLayout
import com.flea.market.ui.component.FleaMarketAppBar
import com.flea.market.ui.component.FleaMarketSnackBar
import com.flea.market.ui.favourite.R
import com.flea.market.ui.preview.FleaMarketPreviews
import com.flea.market.ui.preview.FleaMarketThemePreview

@Composable
internal fun FavouriteListScreen(
    uiState: FavouriteListUiState,
    onHandleIntent: (FavouriteListIntent) -> Unit
) {
    Column {
        FleaMarketAppBar(title = R.string.favourites)

        when (uiState) {
            Loading -> ProductListLoading()
            Empty -> EmptyLayout(
                message = stringResource(id = R.string.empty_favourite),
                icon = painterResource(id = R.drawable.ic_empty_favourite)
            )

            is Content -> {
                FavouriteListContent(uiState = uiState, onHandleIntent = onHandleIntent)

                val snackBarUiState by uiState.snackbarUiState.collectAsStateWithLifecycle()
                FleaMarketSnackBar(
                    snackBarUiState = snackBarUiState,
                    onSnackbarResult = { onHandleIntent(SnackbarResult(it)) }
                )
            }

            is Error -> ErrorLayout(
                errorMessage = stringResource(id = uiState.throwable.toAPIErrorMessage()),
                errorIcon = painterResource(id = uiState.throwable.toAPIErrorIcon())
            )
        }
    }
}

@FleaMarketPreviews
@Composable
private fun FavouriteListScreenLoadingPreview() {
    FleaMarketThemePreview {
        FavouriteListScreen(uiState = Loading, onHandleIntent = {})
    }
}

@FleaMarketPreviews
@Composable
private fun FavouriteListScreenContentPreview() {
    FleaMarketThemePreview {
        FavouriteListScreen(uiState = dummyContent, onHandleIntent = {})
    }
}

@FleaMarketPreviews
@Composable
private fun FavouriteListScreenEmptyPreview() {
    FleaMarketThemePreview {
        FavouriteListScreen(uiState = Empty, onHandleIntent = {})
    }
}

@FleaMarketPreviews
@Composable
private fun FavouriteListScreenErrorPreview() {
    FleaMarketThemePreview {
        FavouriteListScreen(uiState = Error(NetworkException), onHandleIntent = {})
    }
}
