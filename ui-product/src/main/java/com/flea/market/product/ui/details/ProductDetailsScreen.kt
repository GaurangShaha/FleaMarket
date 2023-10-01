package com.flea.market.product.ui.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.flea.market.common.mapper.toAPIErrorIcon
import com.flea.market.common.mapper.toAPIErrorMessage
import com.flea.market.foundation.model.NetworkException
import com.flea.market.product.ui.details.ProductDetailsUiState.Content
import com.flea.market.product.ui.details.ProductDetailsUiState.Error
import com.flea.market.product.ui.details.ProductDetailsUiState.Loading
import com.flea.market.product.ui.details.component.ProductDetailsContent
import com.flea.market.product.ui.details.component.ProductDetailsLoading
import com.flea.market.product.ui.details.component.dummyContent
import com.flea.market.ui.component.ErrorLayout
import com.flea.market.ui.component.FleaMarketAppBar
import com.flea.market.ui.component.HeartToggleButton
import com.flea.market.ui.compositionlocal.LocalNavControllerProvider
import com.flea.market.ui.preview.FleaMarketPreview
import com.flea.market.ui.preview.FleaMarketThemePreview
import com.flea.market.ui.theme.DarkStatusBarDisposableEffect
import com.flea.market.ui.theme.extraColors
import com.flea.product.ui.R

@Composable
internal fun ProductDetailsScreen(
    uiState: ProductDetailsUiState, handleIntent: (ProductDetailsIntent) -> Unit
) {
    DarkStatusBarDisposableEffect()

    Box {
        var actionItems: @Composable (RowScope.() -> Unit) = {}

        when (uiState) {
            is Content -> {
                ProductDetailsContent(state = uiState, handleIntent = handleIntent)

                val addedToFavourite by uiState.markedAsFavouriteFlow.collectAsStateWithLifecycle()
                actionItems = {
                    HeartToggleButton(addedToFavourite = addedToFavourite, toggleMarkAsFavourite = {
                        handleIntent(ProductDetailsIntent.ToggleMarkAsFavourite(it))
                    })
                }
            }

            is Error -> ErrorLayout(errorMessage = stringResource(id = uiState.throwable.toAPIErrorMessage()),
                errorIcon = painterResource(id = uiState.throwable.toAPIErrorIcon()),
                retry = { handleIntent(ProductDetailsIntent.Reload) })

            Loading -> ProductDetailsLoading()
        }

        val navController = LocalNavControllerProvider.current
        FleaMarketAppBar(
            title = R.string.product_details,
            navigationIcon = Icons.Default.ArrowBack to { navController.navigateUp() },
            actionItems = actionItems,
            backgroundColor = Color.Transparent,
            contentColor = MaterialTheme.extraColors.onScrimColor,
            modifier = Modifier.background(Brush.verticalGradient(MaterialTheme.extraColors.scrimColor))
        )
    }
}

@Composable
@FleaMarketPreview
private fun ProductDetailsLoadingScreenPreview() {
    FleaMarketThemePreview {
        ProductDetailsScreen(uiState = Loading, handleIntent = {})
    }
}

@Composable
@FleaMarketPreview
private fun ProductDetailsErrorScreenPreview() {
    FleaMarketThemePreview {
        ProductDetailsScreen(uiState = Error(NetworkException), handleIntent = {})
    }
}

@Composable
@FleaMarketPreview
private fun ProductDetailsContentScreenPreview() {
    FleaMarketThemePreview {
        ProductDetailsScreen(uiState = dummyContent, handleIntent = {})
    }
}