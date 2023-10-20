package com.flea.market.cart.ui.details

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.flea.market.cart.ui.details.CartDetailsUiState.Content
import com.flea.market.cart.ui.details.CartDetailsUiState.Empty
import com.flea.market.cart.ui.details.CartDetailsUiState.Error
import com.flea.market.cart.ui.details.CartDetailsUiState.Loading
import com.flea.market.cart.ui.details.component.CartDetailsContent
import com.flea.market.cart.ui.details.component.CartDetailsLoading
import com.flea.market.cart.ui.details.component.dummyContent
import com.flea.market.common.mapper.toAPIErrorIcon
import com.flea.market.common.mapper.toAPIErrorMessage
import com.flea.market.foundation.model.NetworkException
import com.flea.market.ui.cart.R
import com.flea.market.ui.component.EmptyLayout
import com.flea.market.ui.component.ErrorLayout
import com.flea.market.ui.component.FleaMarketAppBar
import com.flea.market.ui.preview.FleaMarketPreviews
import com.flea.market.ui.preview.FleaMarketThemePreview

@Composable
internal fun CartDetailsScreen(
    uiState: CartDetailsUiState, onHandleIntent: (CartDetailsIntent) -> Unit
) {
    Column {
        FleaMarketAppBar(title = R.string.cart)

        when (uiState) {
            Loading -> CartDetailsLoading()
            Empty -> EmptyLayout(
                message = stringResource(R.string.empty_cart),
                icon = painterResource(id = R.drawable.ic_empty_cart)
            )

            is Content -> CartDetailsContent(uiState = uiState, onHandleIntent = onHandleIntent)

            is Error -> ErrorLayout(
                errorMessage = stringResource(id = uiState.throwable.toAPIErrorMessage()),
                errorIcon = painterResource(id = uiState.throwable.toAPIErrorIcon())
            )
        }
    }
}

@Composable
@FleaMarketPreviews
private fun CartDetailsContentScreenPreview() {
    FleaMarketThemePreview {
        CartDetailsScreen(uiState = dummyContent, onHandleIntent = {})
    }
}

@Composable
@FleaMarketPreviews
private fun CartDetailsEmptyScreenPreview() {
    FleaMarketThemePreview {
        CartDetailsScreen(uiState = Empty, onHandleIntent = {})
    }
}

@Composable
@FleaMarketPreviews
private fun CartDetailsLoadingScreenPreview() {
    FleaMarketThemePreview {
        CartDetailsScreen(uiState = Loading, onHandleIntent = {})
    }
}

@Composable
@FleaMarketPreviews
private fun CartDetailsErrorScreenPreview() {
    FleaMarketThemePreview {
        CartDetailsScreen(uiState = Error(NetworkException), onHandleIntent = {})
    }
}