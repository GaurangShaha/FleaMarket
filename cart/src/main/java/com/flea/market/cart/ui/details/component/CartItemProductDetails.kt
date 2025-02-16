package com.flea.market.cart.ui.details.component

import android.artisan.ui.component.LazyImage
import android.artisan.ui.component.Stepper
import android.artisan.ui.defaults.PRODUCT_IMAGE_ASPECT_RATIO
import android.artisan.ui.theme.extraTypography
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
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
import com.flea.market.cart.ui.R
import com.flea.market.cart.ui.details.entity.ItemsInCartViewEntity
import java.text.NumberFormat

@Composable
internal fun CartItemProductDetails(
    itemsInCart: ItemsInCartViewEntity,
    onDecreaseQuantity: () -> Unit,
    onIncreaseQuantity: () -> Unit,
    onRemoveFromCart: () -> Unit,
    modifier: Modifier = Modifier,
    onNavigateToProductDetails: () -> Unit
) {
    Card(
        modifier = modifier
            .padding(horizontal = 16.dp)
            .clickable { onNavigateToProductDetails() },
        shape = MaterialTheme.shapes.medium,
        elevation = 2.dp,
        backgroundColor = MaterialTheme.colors.secondary
    ) {
        Box(contentAlignment = Alignment.TopEnd) {
            Row(Modifier.fillMaxWidth()) {
                LazyImage(
                    url = itemsInCart.image,
                    modifier = Modifier
                        .height(90.dp)
                        .aspectRatio(PRODUCT_IMAGE_ASPECT_RATIO)
                        .padding(1.dp)
                        .clip(MaterialTheme.shapes.medium)
                )

                ItemDetailsSection(itemsInCart, onDecreaseQuantity, onIncreaseQuantity)
            }

            RemoveFromCartButton(onRemoveFromCart)
        }
    }
}

@Composable
private fun RemoveFromCartButton(onRemoveFromCart: () -> Unit) {
    Surface(
        modifier = Modifier
            .height(32.dp)
            .width(40.dp)
            .clickable { onRemoveFromCart() }
            .padding(bottom = 6.dp),
        color = MaterialTheme.colors.error,
        shape = RoundedCornerShape(topEnd = 16.dp, bottomStart = 16.dp)
    ) {
        Icon(
            modifier = Modifier.padding(4.dp),
            imageVector = Icons.Default.Clear,
            contentDescription = null
        )
    }
}

@Composable
private fun RowScope.ItemDetailsSection(
    itemsInCart: ItemsInCartViewEntity,
    onDecreaseQuantity: () -> Unit,
    onIncreaseQuantity: () -> Unit
) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .weight(1f)
    ) {
        Text(
            modifier = Modifier.padding(end = 32.dp),
            text = itemsInCart.title,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.extraTypography.body1Bold
        )

        Text(
            modifier = Modifier.padding(top = 4.dp),
            text = stringResource(id = R.string.free_shipping),
            style = MaterialTheme.extraTypography.captionDarkGray
        )

        Row(verticalAlignment = Alignment.CenterVertically) {
            val currencyFormatter = remember {
                NumberFormat.getCurrencyInstance().apply { maximumFractionDigits = 2 }
            }
            Text(
                modifier = Modifier.weight(1f),
                text = currencyFormatter.format(itemsInCart.price),
                style = MaterialTheme.extraTypography.body1Bold
            )

            Stepper(
                quantity = itemsInCart.quantity,
                onIncreaseQuantity = { onIncreaseQuantity() },
                onDecreaseQuantity = { onDecreaseQuantity() }
            )
        }
    }
}
