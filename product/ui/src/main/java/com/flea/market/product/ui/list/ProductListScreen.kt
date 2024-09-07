package com.flea.market.product.ui.list

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.flea.market.common.mapper.toAPIErrorIcon
import com.flea.market.common.mapper.toAPIErrorMessage
import com.flea.market.foundation.model.InternetConnectionException
import com.flea.market.product.ui.list.ProductListUiState.Content
import com.flea.market.product.ui.list.ProductListUiState.Error
import com.flea.market.product.ui.list.ProductListUiState.Loading
import com.flea.market.product.ui.list.component.ProductListContent
import com.flea.market.product.ui.list.component.ProductListLoading
import com.flea.market.product.ui.list.component.dummyProductListContent
import com.flea.market.ui.component.ErrorLayout
import com.flea.market.ui.component.FleaMarketAppBar
import com.flea.market.ui.preview.FleaMarketPreviews
import com.flea.market.ui.preview.FleaMarketThemePreview

@Composable
internal fun ProductListScreen(
    state: ProductListUiState,
    onHandleIntent: (ProductListIntent) -> Unit
) {
    Column {
        FleaMarketAppBar(title = com.flea.market.ui.common.R.string.app_name)
        when (state) {
            is Error -> ErrorLayout(
                errorMessage = stringResource(id = state.throwable.toAPIErrorMessage()),
                errorIcon = painterResource(id = state.throwable.toAPIErrorIcon())
            ) { onHandleIntent(ProductListIntent.Reload) }

            Loading -> ProductListLoading()

            is Content -> ProductListContent(
                state = state,
                onHandleIntent = onHandleIntent
            )
        }
    }
}

@FleaMarketPreviews
@Composable
private fun ProductListScreenLoadingPreview() {
    FleaMarketThemePreview {
        ProductListScreen(state = Loading, onHandleIntent = {})
    }
}

@FleaMarketPreviews
@Composable
private fun ProductListScreenErrorPreview() {
    FleaMarketThemePreview {
        ProductListScreen(state = Error(throwable = InternetConnectionException), onHandleIntent = {})
    }
}

@FleaMarketPreviews
@Composable
private fun ProductListScreenContentPreview() {
    FleaMarketThemePreview {
        ProductListScreen(state = dummyProductListContent, onHandleIntent = {})
    }
}
