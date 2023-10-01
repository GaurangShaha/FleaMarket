package com.flea.market.product.ui.list.component

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.flea.market.ui.component.LazyImage
import com.flea.market.ui.preview.FleaMarketPreview
import com.flea.market.ui.preview.FleaMarketThemePreview
import com.flea.market.ui.theme.extraTypography
import com.flea.market.product.ui.common.entity.ProductDetailsViewEntity
import java.text.NumberFormat

@OptIn(ExperimentalMaterialApi::class)
@Composable
internal fun ProductListItem(
    modifier: Modifier = Modifier,
    productDetails: com.flea.market.product.ui.common.entity.ProductDetailsViewEntity,
    onProductClick: (productId: Int) -> Unit
) {
    Box(contentAlignment = Alignment.Center) {
        Card(
            onClick = { onProductClick(productDetails.id) },
            shape = MaterialTheme.shapes.large,
            backgroundColor = MaterialTheme.colors.secondary,
            modifier = modifier.width(150.dp),
            elevation = 2.dp
        ) {
            Column {
                LazyImage(
                    modifier = Modifier
                        .height(200.dp)
                        .width(150.dp)
                        .padding(1.dp)
                        .clip(MaterialTheme.shapes.large), url = productDetails.imageList.first()
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
                        start = 10.dp, end = 10.dp, top = 2.dp, bottom = 8.dp
                    ),
                    style = MaterialTheme.extraTypography.body1Bold
                )
            }
        }
    }
}

@FleaMarketPreview
@Composable
private fun ProductListItemPreview() {
    FleaMarketThemePreview {
        ProductListItem(productDetails = dummyProductList.first(), onProductClick = {})
    }
}