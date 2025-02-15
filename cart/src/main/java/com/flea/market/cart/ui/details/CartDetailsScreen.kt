package com.flea.market.cart.ui.details

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.flea.market.cart.ui.R
import com.flea.market.cart.ui.details.CartDetailsUiState.Content
import com.flea.market.cart.ui.details.CartDetailsUiState.Empty
import com.flea.market.cart.ui.details.CartDetailsUiState.Error
import com.flea.market.cart.ui.details.CartDetailsUiState.Loading
import com.flea.market.cart.ui.details.component.CartDetailsContent
import com.flea.market.cart.ui.details.component.CartDetailsLoading
import com.flea.market.common.mapper.toAPIErrorIcon
import com.flea.market.common.mapper.toAPIErrorMessage
import com.flea.market.ui.component.EmptyLayout
import com.flea.market.ui.component.ErrorLayout
import com.flea.market.ui.component.FleaMarketAppBar

@Composable
internal fun CartDetailsScreen(
    uiState: CartDetailsUiState,
    processIntent: (CartDetailsIntent) -> Unit
) {
    Column {
        FleaMarketAppBar(navigationIcon = Icons.Default.Menu to {}, title = R.string.cart)

        when (uiState) {
            Loading -> CartDetailsLoading()
            Empty -> EmptyLayout(
                message = stringResource(R.string.empty_cart),
                icon = painterResource(id = R.drawable.ic_empty_cart)
            )

            is Content -> CartDetailsContent(uiState = uiState, processIntent = processIntent)

            is Error -> ErrorLayout(
                errorMessage = stringResource(id = uiState.throwable.toAPIErrorMessage()),
                errorIcon = painterResource(id = uiState.throwable.toAPIErrorIcon())
            )
        }
    }
}
