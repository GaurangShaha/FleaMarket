package com.flea.market.product.ui.details.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
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
import kotlinx.coroutines.delay

@Composable
@OptIn(ExperimentalFoundationApi::class)
internal fun PagerWithIndicator(
    modifier: Modifier = Modifier,
    uiState: Content,
    scrollState: ScrollState? = null,
    delayForSwitchingImage: Long = 0
) {
    Box(contentAlignment = Alignment.BottomCenter, modifier = Modifier.graphicsLayer {
        scrollState?.value?.let { translationY = 0.4f * it
        }
    }) {
        val pagerState = rememberPagerState()
        HorizontalPager(
            state = pagerState,
            pageCount = uiState.productDetails.imageList.size,
            modifier = modifier.aspectRatio(0.75f)
        ) {
            LazyImage(
                url = uiState.productDetails.imageList[it], modifier = modifier.aspectRatio(0.75f)
            )
        }

        PageIndicator(modifier = Modifier.padding(bottom = scrollState?.let { 40.dp } ?: 8.dp,
            start = 8.dp,
            end = 8.dp),
            totalPages = uiState.productDetails.imageList.size,
            currentPage = pagerState.currentPage)

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
