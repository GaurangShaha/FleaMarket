package com.flea.market.favorite.ui.list.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.flea.market.common.navigation.ProductDetailsDeepLink
import com.flea.market.favorite.ui.list.entity.FavouriteItemViewEntity
import com.flea.market.favourite.ui.R
import com.flea.market.ui.component.LazyImage
import com.flea.market.ui.compositionlocal.LocalNavController
import com.flea.market.ui.preview.FleaMarketPreviews
import com.flea.market.ui.preview.FleaMarketThemePreview
import com.flea.market.ui.theme.extraTypography
import java.text.NumberFormat

@OptIn(ExperimentalMaterialApi::class)
@Composable
internal fun FavouriteProductItem(
    favouriteItem: FavouriteItemViewEntity,
    onRemoveFromFavourite: () -> Unit,
    onMoveToCart: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.clip(MaterialTheme.shapes.large),
        contentAlignment = Alignment.Center,
    ) {
        Box(
            modifier = Modifier.clip(MaterialTheme.shapes.large),
            contentAlignment = Alignment.TopEnd
        ) {
            val navController = LocalNavController.current
            Card(
                onClick = { navController.navigate(ProductDetailsDeepLink.getUri(favouriteItem.id)) },
                backgroundColor = MaterialTheme.colors.secondary,
                modifier = Modifier.width(156.dp),
                elevation = 2.dp,
            ) {
                Column {
                    LazyImage(
                        url = favouriteItem.image,
                        modifier = Modifier
                            .height(208.dp)
                            .width(156.dp)
                            .padding(1.dp)
                            .clip(MaterialTheme.shapes.large)
                    )

                    Text(
                        text = favouriteItem.title,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.padding(start = 10.dp, end = 10.dp)
                    )

                    Text(
                        text = favouriteItem.category,
                        style = MaterialTheme.extraTypography.captionDarkGray,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.padding(start = 10.dp, end = 10.dp)
                    )

                    val currencyFormat = remember { NumberFormat.getCurrencyInstance() }
                    Text(
                        text = currencyFormat.format(favouriteItem.price),
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

                    AddToCartButton(onMoveToCart)
                }
            }

            RemoveFromFavouriteButton(onRemoveFromFavourite)
        }
    }
}

@Composable
private fun AddToCartButton(onMoveToCart: () -> Unit) {
    Button(
        modifier = Modifier
            .padding(1.dp)
            .fillMaxWidth()
            .height(44.dp),
        shape = MaterialTheme.shapes.large,
        elevation = ButtonDefaults.elevation(0.dp, 0.dp, 0.dp, 0.dp, 0.dp),
        onClick = { onMoveToCart() }
    ) {
        Text(
            text = stringResource(id = R.string.move_to_cart),
            maxLines = 1,
            overflow = TextOverflow.Visible,
            softWrap = false
        )
    }
}

@Composable
private fun RemoveFromFavouriteButton(onRemoveFromFavourite: () -> Unit) {
    Surface(
        modifier = Modifier
            .height(32.dp)
            .width(44.dp)
            .padding(top = 1.dp, end = 1.dp)
            .clickable { onRemoveFromFavourite() },
        color = MaterialTheme.colors.error,
        shape = RoundedCornerShape(topEnd = 22.dp, bottomStart = 22.dp)
    ) {
        Icon(
            modifier = Modifier.padding(6.dp),
            imageVector = Icons.Default.Clear,
            contentDescription = null
        )
    }
}

@FleaMarketPreviews
@Composable
private fun ProductListItemPreview() {
    FleaMarketThemePreview {
        FavouriteProductItem(
            favouriteItem = dummyFavouriteItemList.first(),
            onRemoveFromFavourite = {},
            onMoveToCart = {}
        )
    }
}
