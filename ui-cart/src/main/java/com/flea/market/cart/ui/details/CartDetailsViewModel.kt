package com.flea.market.cart.ui.details

import androidx.lifecycle.viewModelScope
import com.flea.market.cart.local.entity.CartProductDetailsEntity
import com.flea.market.cart.repository.CartRepository
import com.flea.market.cart.ui.details.CartDetailsIntent.DecreaseQuantity
import com.flea.market.cart.ui.details.CartDetailsIntent.IncreaseQuantity
import com.flea.market.cart.ui.details.CartDetailsIntent.RemoveFromCart
import com.flea.market.cart.ui.details.CartDetailsUiState.Content
import com.flea.market.cart.ui.details.CartDetailsUiState.Empty
import com.flea.market.cart.ui.details.CartDetailsUiState.Error
import com.flea.market.cart.ui.details.CartDetailsUiState.Loading
import com.flea.market.cart.ui.details.entity.ItemsInCartViewEntity
import com.flea.market.cart.ui.details.entity.PriceDetailsViewEntity
import com.flea.market.cart.ui.details.mapper.toItemsInCartViewEntity
import com.flea.market.common.base.viewmodel.BaseViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

private const val DELAY_FOR_SIMULATING_LOADING = 1000L

private const val DISCOUNT_AND_TAX_PERCENTAGE = 0.1

internal class CartDetailsViewModel(private val cartRepository: CartRepository) :
    BaseViewModel<CartDetailsIntent, CartDetailsUiState>(Loading) {

    init {
        cartRepository.getAllItems().map { productList ->
            if (productList.isNotEmpty()) {
                Content(
                    productList = productList.map { it.toItemsInCartViewEntity() },
                    priceDetails = getPriceDetails(productList)
                )
            } else {
                Empty
            }
        }.catch { emit(Error(it)) }.onStart { delay(DELAY_FOR_SIMULATING_LOADING) }
            .collectAndUpdateUiState()
    }

    override fun onHandleIntent(intent: CartDetailsIntent) = when (intent) {
        is DecreaseQuantity -> decreaseQuantity(intent.itemsInCartViewEntity)
        is IncreaseQuantity -> increaseQuantity(intent.itemsInCartViewEntity)
        is RemoveFromCart -> removeFromCart(intent.itemsInCartViewEntity)
        CartDetailsIntent.Checkout -> Unit
    }


    private fun increaseQuantity(itemsInCartViewEntity: ItemsInCartViewEntity) {
        viewModelScope.launch {
            cartRepository.updateQuantity(
                productId = itemsInCartViewEntity.id,
                quantity = itemsInCartViewEntity.quantity.inc()
            )
        }
    }

    private fun removeFromCart(itemsInCartViewEntity: ItemsInCartViewEntity) {
        viewModelScope.launch {
            cartRepository.removeItem(itemsInCartViewEntity.id)
        }
    }

    private fun decreaseQuantity(itemsInCartViewEntity: ItemsInCartViewEntity) {
        viewModelScope.launch {
            val newQuantity = itemsInCartViewEntity.quantity.dec()
            if (newQuantity > 0) {
                cartRepository.updateQuantity(itemsInCartViewEntity.id, newQuantity)
            } else {
                removeFromCart(itemsInCartViewEntity)
            }
        }

    }

    private fun getPriceDetails(productList: List<CartProductDetailsEntity>): PriceDetailsViewEntity {
        val subTotal = productList.sumOf { it.price * it.quantity }
        val discount = subTotal * DISCOUNT_AND_TAX_PERCENTAGE
        val tax = (subTotal - discount) * DISCOUNT_AND_TAX_PERCENTAGE
        val totalPayable = subTotal - discount + tax

        return PriceDetailsViewEntity(subTotal, discount, tax, totalPayable)
    }
}