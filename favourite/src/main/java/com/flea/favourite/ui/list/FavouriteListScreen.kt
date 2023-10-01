package com.flea.favourite.ui.list

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.flea.common.ui.component.EmptyLayout
import com.flea.common.ui.component.ErrorLayout
import com.flea.common.ui.component.FleaMarketAppBar
import com.flea.common.ui.component.FleaMarketSnackBar
import com.flea.common.ui.component.preview.FleaMarketPreview
import com.flea.common.ui.component.preview.FleaMarketThemePreview
import com.flea.common.ui.mapper.toAPIErrorIcon
import com.flea.common.ui.mapper.toAPIErrorMessage
import com.flea.favourite.R
import com.flea.favourite.ui.list.FavouriteListUiState.Content
import com.flea.favourite.ui.list.FavouriteListUiState.Empty
import com.flea.favourite.ui.list.FavouriteListUiState.Error
import com.flea.favourite.ui.list.FavouriteListUiState.Loading
import com.flea.favourite.ui.list.component.FavouriteListContent
import com.flea.favourite.ui.list.component.ProductListLoading
import com.flea.favourite.ui.list.component.dummyContent
import com.flea.market.foundation.model.NetworkException

@Composable
internal fun FavouriteListScreen(
    uiState: FavouriteListUiState,
    handleIntent: (FavouriteListIntent) -> Unit,
    notifySnackbarResult: (Boolean) -> Unit
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
                FavouriteListContent(uiState = uiState, handleIntent = handleIntent)

                val snackBarUiState by uiState.snackbarUiState.collectAsStateWithLifecycle()
                FleaMarketSnackBar(
                    snackBarUiState = snackBarUiState, notifySnackbarResult = notifySnackbarResult
                )
            }

            is Error -> ErrorLayout(
                errorMessage = stringResource(id = uiState.throwable.toAPIErrorMessage()),
                errorIcon = painterResource(id = uiState.throwable.toAPIErrorIcon())
            )
        }
    }
}

@FleaMarketPreview
@Composable
private fun FavouriteListScreenLoadingPreview() {
    FleaMarketThemePreview {
        FavouriteListScreen(uiState = Loading, handleIntent = {}, notifySnackbarResult = {})
    }
}

@FleaMarketPreview
@Composable
private fun FavouriteListScreenContentPreview() {
    FleaMarketThemePreview {
        FavouriteListScreen(uiState = dummyContent, handleIntent = {}, notifySnackbarResult = {})
    }
}

@FleaMarketPreview
@Composable
private fun FavouriteListScreenEmptyPreview() {
    FleaMarketThemePreview {
        FavouriteListScreen(uiState = Empty, handleIntent = {}, notifySnackbarResult = {})
    }
}

@FleaMarketPreview
@Composable
private fun FavouriteListScreenErrorPreview() {
    FleaMarketThemePreview {
        FavouriteListScreen(uiState = Error(NetworkException),
            handleIntent = {},
            notifySnackbarResult = {})
    }
}