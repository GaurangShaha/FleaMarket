package com.flea.market.product.ui.list.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.flea.market.product.ui.common.entity.ProductDetailsViewEntity
import com.flea.market.ui.component.LazyImage
import com.flea.market.ui.preview.FleaMarketPreviews
import com.flea.market.ui.preview.FleaMarketThemePreview
import com.flea.market.ui.theme.extraTypography
import java.text.NumberFormat

@OptIn(ExperimentalMaterialApi::class)
@Composable
internal fun ProductListItem(
    productDetails: ProductDetailsViewEntity,
    modifier: Modifier = Modifier,
    onProductClick: () -> Unit
) {
    Box(contentAlignment = Alignment.Center) {
        Card(
            onClick = { onProductClick() },
            shape = MaterialTheme.shapes.large,
            backgroundColor = MaterialTheme.colors.secondary,
            modifier = modifier.width(150.dp),
            elevation = 2.dp
        ) {
            Column {
                LazyImage(
                    url = productDetails.imageList.first(),
                    modifier = Modifier
                        .height(200.dp)
                        .width(150.dp)
                        .padding(1.dp)
                        .clip(MaterialTheme.shapes.large)
                )

                Text(
                    text = productDetails.title,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(start = 10.dp, end = 10.dp)
                )

                Text(
                    text = productDetails.category,
                    style = MaterialTheme.extraTypography.captionDarkGray,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(start = 10.dp, end = 10.dp)
                )

                val currencyFormat = remember { NumberFormat.getCurrencyInstance() }
                Text(
                    text = currencyFormat.format(productDetails.price),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(
                        start = 10.dp,
                        end = 10.dp,
                        top = 2.dp,
                        bottom = 8.dp
                    ),
                    style = MaterialTheme.extraTypography.body1Bold
                )
            }
        }
    }
}

@FleaMarketPreviews
@Composable
internal fun ProductListItemPreview() {
    FleaMarketThemePreview {
        ProductListItem(productDetails = dummyProductList.first()) {}
    }
}
