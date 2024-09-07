package com.flea.market.product.ui.details.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.flea.market.product.ui.details.ProductDetailsUiState.Content
import com.flea.market.ui.theme.extraColors
import com.flea.market.ui.theme.extraTypography
import com.gowtham.ratingbar.ComposeStars
import com.gowtham.ratingbar.RatingBarStyle
import java.text.NumberFormat

@Composable
internal fun ProductInformation(state: Content, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(
            text = state.productDetails.title,
            style = MaterialTheme.extraTypography.h6Bold,

        )
        Text(
            text = state.productDetails.category,
            style = MaterialTheme.extraTypography.captionDarkGray
        )

        Row(
            Modifier
                .fillMaxWidth()
                .padding(top = 4.dp, bottom = 4.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            val numberFormatter = remember {
                NumberFormat.getCurrencyInstance().apply { maximumFractionDigits = 2 }
            }
            Text(
                text = numberFormatter.format(state.productDetails.price),
                style = MaterialTheme.extraTypography.h6Bold,

            )

            ComposeStars(
                value = state.productDetails.rate.toFloat(),
                numOfStars = 5,
                size = 28.dp,
                spaceBetween = 4.dp,
                hideInactiveStars = false,
                style = RatingBarStyle.Fill(
                    activeColor = MaterialTheme.colors.primary,
                    inActiveColor = MaterialTheme.extraColors.inActiveStarColor
                ),
                painterEmpty = null,
                painterFilled = null
            )
        }

        Text(
            text = state.productDetails.description,
            style = MaterialTheme.extraTypography.body1DarkGray
        )
    }
}
