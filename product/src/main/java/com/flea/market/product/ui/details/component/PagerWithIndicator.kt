package com.flea.market.product.ui.details.component

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import com.flea.market.product.ui.details.ProductDetailsUiState.Content
import com.flea.market.ui.component.LazyImage
import com.flea.market.ui.component.PageIndicator
import com.flea.market.ui.defaults.PRODUCT_IMAGE_ASPECT_RATIO
import kotlinx.coroutines.delay

private const val PARALLAX_FACTOR = 0.4f

@Composable
internal fun PagerWithIndicator(
    uiState: Content,
    modifier: Modifier = Modifier,
    delayForSwitchingImage: Long = 0,
    scrollState: ScrollState? = null
) {
    Box(
        contentAlignment = Alignment.BottomCenter,
        modifier = Modifier.graphicsLayer {
            scrollState?.value?.let {
                translationY = PARALLAX_FACTOR * it
            }
        }
    ) {
        val pagerState = rememberPagerState { uiState.productDetails.imageList.size }
        HorizontalPager(
            state = pagerState,
            modifier = modifier.aspectRatio(PRODUCT_IMAGE_ASPECT_RATIO)
        ) {
            LazyImage(
                url = uiState.productDetails.imageList[it],
                modifier = Modifier.fillMaxSize()
            )
        }

        PageIndicator(
            totalPages = uiState.productDetails.imageList.size,
            currentPage = pagerState.currentPage,
            modifier = Modifier.padding(
                bottom = scrollState?.let { 40.dp } ?: 8.dp,
                start = 8.dp,
                end = 8.dp
            )
        )

        if (delayForSwitchingImage != 0L) {
            LaunchedEffect(pagerState.settledPage) {
                while (true) {
                    delay(delayForSwitchingImage)
                    pagerState.animateScrollToPage(pagerState.currentPage.inc() % uiState.productDetails.imageList.size)
                }
            }
        }
    }
}
