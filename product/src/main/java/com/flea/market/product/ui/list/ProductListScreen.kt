package com.flea.market.product.ui.list

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.flea.market.common.mapper.toAPIErrorIcon
import com.flea.market.common.mapper.toAPIErrorMessage
import com.flea.market.product.ui.list.ProductListIntent.Reload
import com.flea.market.product.ui.list.ProductListUiState.Content
import com.flea.market.product.ui.list.ProductListUiState.Error
import com.flea.market.product.ui.list.ProductListUiState.Loading
import com.flea.market.product.ui.list.component.ProductListContent
import com.flea.market.product.ui.list.component.ProductListLoading
import com.flea.market.ui.component.ErrorLayout
import com.flea.market.ui.component.FleaMarketAppBar

@Composable
internal fun ProductListScreen(
    state: ProductListUiState,
    processIntent: (ProductListIntent) -> Unit
) {
    Column {
        FleaMarketAppBar(
            navigationIcon = Icons.Default.Menu to {},
            title = com.flea.market.ui.common.R.string.app_name
        )
        when (state) {
            is Error -> ErrorLayout(
                errorMessage = stringResource(id = state.throwable.toAPIErrorMessage()),
                errorIcon = painterResource(id = state.throwable.toAPIErrorIcon()),
                onRetry = { processIntent(Reload) }
            )

            Loading -> ProductListLoading()

            is Content -> ProductListContent(
                state = state,
                processIntent = processIntent
            )
        }
    }
}
