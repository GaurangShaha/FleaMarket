package com.flea.cart.ui.details

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.flea.cart.R
import com.flea.cart.ui.details.CartDetailsUiState.Content
import com.flea.cart.ui.details.CartDetailsUiState.Empty
import com.flea.cart.ui.details.CartDetailsUiState.Error
import com.flea.cart.ui.details.CartDetailsUiState.Loading
import com.flea.cart.ui.details.component.CartDetailsContent
import com.flea.cart.ui.details.component.CartDetailsLoading
import com.flea.cart.ui.details.component.dummyContent
import com.flea.common.ui.component.EmptyLayout
import com.flea.common.ui.component.ErrorLayout
import com.flea.common.ui.component.FleaMarketAppBar
import com.flea.common.ui.component.preview.FleaMarketPreview
import com.flea.common.ui.component.preview.FleaMarketThemePreview
import com.flea.common.ui.mapper.toAPIErrorIcon
import com.flea.common.ui.mapper.toAPIErrorMessage
import com.flea.market.foundation.model.NetworkException

@Composable
internal fun CartDetailsScreen(
    uiState: CartDetailsUiState, handleIntent: (CartDetailsIntent) -> Unit
) {
    Column {
        FleaMarketAppBar(title = R.string.cart)

        when (uiState) {
            Loading -> CartDetailsLoading()
            Empty -> EmptyLayout(
                message = stringResource(R.string.empty_cart),
                icon = painterResource(id = R.drawable.ic_empty_cart)
            )

            is Content -> CartDetailsContent(uiState = uiState, handleIntent = handleIntent)

            is Error -> ErrorLayout(
                errorMessage = stringResource(id = uiState.throwable.toAPIErrorMessage()),
                errorIcon = painterResource(id = uiState.throwable.toAPIErrorIcon())
            )
        }
    }
}

@Composable
@FleaMarketPreview
private fun CartDetailsContentScreenPreview() {
    FleaMarketThemePreview {
        CartDetailsScreen(uiState = dummyContent, handleIntent = {})
    }
}

@Composable
@FleaMarketPreview
private fun CartDetailsEmptyScreenPreview() {
    FleaMarketThemePreview {
        CartDetailsScreen(uiState = Empty, handleIntent = {})
    }
}

@Composable
@FleaMarketPreview
private fun CartDetailsLoadingScreenPreview() {
    FleaMarketThemePreview {
        CartDetailsScreen(uiState = Loading, handleIntent = {})
    }
}

@Composable
@FleaMarketPreview
private fun CartDetailsErrorScreenPreview() {
    FleaMarketThemePreview {
        CartDetailsScreen(uiState = Error(NetworkException), handleIntent = {})
    }
}