package com.flea.market.cart.ui.details.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.flea.market.cart.ui.details.entity.PriceDetailsViewEntity
import com.flea.market.ui.cart.R.string
import com.flea.market.ui.preview.FleaMarketPreviews
import com.flea.market.ui.preview.FleaMarketThemePreview
import com.flea.market.ui.theme.extraColors
import com.flea.market.ui.theme.extraTypography
import java.text.NumberFormat

@Composable
internal fun PriceDetails(
    priceDetails: PriceDetailsViewEntity,
    modifier: Modifier = Modifier
) {
    val currencyFormatter = remember {
        NumberFormat.getCurrencyInstance().apply {
            maximumFractionDigits = 2
        }
    }

    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier.padding(start = 24.dp, end = 24.dp)
    ) {
        SubtotalRow(currencyFormatter, priceDetails)

        Divider(color = MaterialTheme.extraColors.dividerColor)

        DiscountRow(currencyFormatter, priceDetails)

        Divider(color = MaterialTheme.extraColors.dividerColor)

        TaxRow(currencyFormatter, priceDetails)

        Divider(color = MaterialTheme.extraColors.dividerColor)

        DeliveryRow()

        Divider(color = MaterialTheme.extraColors.dividerColor)

        TotalPayableRow(currencyFormatter, priceDetails)
    }
}

@Composable
private fun TotalPayableRow(
    currencyFormatter: NumberFormat,
    priceDetails: PriceDetailsViewEntity
) {
    Row {
        Text(
            text = stringResource(string.total_payable),
            modifier = Modifier.weight(1f),
            style = MaterialTheme.extraTypography.body1DarkGray
        )
        Text(
            text = currencyFormatter.format(priceDetails.totalPayable),
            style = MaterialTheme.extraTypography.body1Bold,

        )
    }
}

@Composable
private fun DeliveryRow() {
    Row {
        Text(
            text = stringResource(string.delivery),
            modifier = Modifier.weight(1f),
            style = MaterialTheme.extraTypography.body1DarkGray
        )
        Text(
            text = stringResource(string.free_shipping),
            style = MaterialTheme.extraTypography.body1Bold,

        )
    }
}

@Composable
private fun TaxRow(
    currencyFormatter: NumberFormat,
    priceDetails: PriceDetailsViewEntity
) {
    Row {
        Text(
            text = stringResource(string.tax),
            modifier = Modifier.weight(1f),
            style = MaterialTheme.extraTypography.body1DarkGray
        )
        Text(
            text = currencyFormatter.format(priceDetails.tax),
            style = MaterialTheme.extraTypography.body1Bold,

        )
    }
}

@Composable
private fun DiscountRow(
    currencyFormatter: NumberFormat,
    priceDetails: PriceDetailsViewEntity
) {
    Row {
        Text(
            text = stringResource(string.discount),
            modifier = Modifier.weight(1f),
            style = MaterialTheme.extraTypography.body1DarkGray
        )
        Text(
            text = "-" + currencyFormatter.format(priceDetails.discount),
            style = MaterialTheme.extraTypography.body1Bold,

        )
    }
}

@Composable
private fun SubtotalRow(
    currencyFormatter: NumberFormat,
    priceDetails: PriceDetailsViewEntity
) {
    Row {
        Text(
            text = stringResource(string.sub_total),
            modifier = Modifier.weight(1f),
            style = MaterialTheme.extraTypography.body1DarkGray
        )
        Text(
            text = currencyFormatter.format(priceDetails.subTotal),
            style = MaterialTheme.extraTypography.body1Bold,

        )
    }
}

@Composable
@FleaMarketPreviews
internal fun PriceDetailsPreview() {
    FleaMarketThemePreview {
        PriceDetails(
            priceDetails = PriceDetailsViewEntity(
                subTotal = 100.toDouble(),
                discount = 10.toDouble(),
                tax = 12.toDouble(),
                totalPayable = 102.toDouble()
            )
        )
    }
}
