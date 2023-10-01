package com.flea.market.favorite.ui.list.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
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
import com.flea.favourite.R
import com.flea.market.common.navigation.ProductDetailsDeepLink
import com.flea.market.favorite.ui.list.entity.FavouriteItemViewEntity
import com.flea.market.ui.component.LazyImage
import com.flea.market.ui.compositionlocal.LocalNavControllerProvider
import com.flea.market.ui.preview.FleaMarketPreview
import com.flea.market.ui.preview.FleaMarketThemePreview
import com.flea.market.ui.theme.extraTypography
import java.text.NumberFormat

@OptIn(ExperimentalMaterialApi::class)
@Composable
internal fun FavouriteProductItem(
    modifier: Modifier = Modifier,
    favouriteItem: FavouriteItemViewEntity,
    removeFromFavourite: (productId: Int) -> Unit,
    moveToCart: (FavouriteItemViewEntity) -> Unit
) {
    Box(
        modifier = modifier.clip(MaterialTheme.shapes.large),
        contentAlignment = Alignment.Center,
    ) {
        Box(
            modifier = Modifier.clip(MaterialTheme.shapes.large),
            contentAlignment = Alignment.TopEnd
        ) {
            val navController = LocalNavControllerProvider.current
            Card(
                onClick = { navController.navigate(ProductDetailsDeepLink.getUri(favouriteItem.id)) },
                backgroundColor = MaterialTheme.colors.secondary,
                modifier = Modifier.width(156.dp),
                elevation = 2.dp,
            ) {
                Column {
                    LazyImage(
                        modifier = Modifier
                            .height(208.dp)
                            .width(156.dp)
                            .padding(1.dp)
                            .clip(MaterialTheme.shapes.large), url = favouriteItem.image
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
                            start = 10.dp, end = 10.dp, top = 2.dp, bottom = 8.dp
                        ),
                        style = MaterialTheme.extraTypography.body1Bold
                    )

                    Button(modifier = Modifier
                        .padding(1.dp)
                        .fillMaxWidth()
                        .height(44.dp),
                        shape = MaterialTheme.shapes.large,
                        elevation = ButtonDefaults.elevation(0.dp, 0.dp, 0.dp, 0.dp, 0.dp),
                        onClick = { moveToCart(favouriteItem) }) {
                        Text(
                            text = stringResource(id = R.string.move_to_cart),
                            maxLines = 1,
                            overflow = TextOverflow.Visible,
                            softWrap = false
                        )
                    }
                }
            }

            Surface(
                modifier = Modifier
                    .height(32.dp)
                    .width(44.dp)
                    .padding(top = 1.dp, end = 1.dp)
                    .clickable { removeFromFavourite(favouriteItem.id) },
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
    }
}

@FleaMarketPreview
@Composable
private fun ProductListItemPreview() {
    FleaMarketThemePreview {
        FavouriteProductItem(favouriteItem = FavouriteItemViewEntity(
            title = "Mens Cotton Jacket",
            id = 0,
            price = 55.99,
            description = "great outerwear jackets for Spring/Autumn/Winter, suitable for many occasions, such as working, hiking, camping, mountain/rock climbing, cycling, traveling or other outdoors. Good gift choice for you or your family member. A warm hearted love to Father, husband or son in this thanksgiving or Christmas Day.",
            category = "Men's clothing",
            image = "https://fakestoreapi.com/img/71li-ujtlUL._AC_UX679_.jpg",
            rating = 4.7
        ), removeFromFavourite = {}, moveToCart = {})
    }
}