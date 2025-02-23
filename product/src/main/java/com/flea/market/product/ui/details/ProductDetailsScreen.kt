package com.flea.market.product.ui.details

import android.artisan.ui.common.mapper.toAPIErrorIcon
import android.artisan.ui.common.mapper.toAPIErrorMessage
import android.artisan.ui.component.ErrorLayout
import android.artisan.ui.component.FleaMarketAppBar
import android.artisan.ui.component.HeartToggleButton
import android.artisan.ui.compositionlocal.LocalNavController
import android.artisan.ui.theme.DarkStatusBarDisposableEffect
import android.artisan.ui.theme.extraColors
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.flea.market.product.ui.R
import com.flea.market.product.ui.details.ProductDetailsIntent.Reload
import com.flea.market.product.ui.details.ProductDetailsIntent.ToggleMarkAsFavourite
import com.flea.market.product.ui.details.ProductDetailsUiState.Content
import com.flea.market.product.ui.details.ProductDetailsUiState.Error
import com.flea.market.product.ui.details.ProductDetailsUiState.Loading
import com.flea.market.product.ui.details.component.ProductDetailsContent
import com.flea.market.product.ui.details.component.ProductDetailsLoading

@Composable
internal fun ProductDetailsScreen(
    uiState: ProductDetailsUiState,
    processIntent: (ProductDetailsIntent) -> Unit
) {
    Surface {
        DarkStatusBarDisposableEffect()

        Box {
            var actionItems: @Composable (RowScope.() -> Unit) = {}

            when (uiState) {
                is Content -> {
                    ProductDetailsContent(state = uiState, processIntent = processIntent)

                    actionItems = {
                        HeartToggleButton(
                            markedAsFavourite = uiState.markedAsFavourite,
                            onToggleMarkAsFavourite = {
                                processIntent(ToggleMarkAsFavourite(it))
                            }
                        )
                    }
                }

                is Error -> ErrorLayout(
                    errorMessage = stringResource(id = uiState.throwable.toAPIErrorMessage()),
                    errorIcon = painterResource(id = uiState.throwable.toAPIErrorIcon()),
                    onRetry = { processIntent(Reload) }
                )

                Loading -> ProductDetailsLoading()
            }

            val navController = LocalNavController.current
            FleaMarketAppBar(
                title = R.string.product_details,
                modifier = Modifier.background(Brush.verticalGradient(MaterialTheme.extraColors.scrimColor)),
                navigationIcon = Icons.AutoMirrored.Filled.ArrowBack to { navController.navigateUp() },
                actionItems = actionItems,
                backgroundColor = Color.Transparent,
                contentColor = MaterialTheme.extraColors.onScrimColor
            )
        }
    }
}
