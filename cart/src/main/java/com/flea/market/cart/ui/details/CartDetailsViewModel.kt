package com.flea.market.cart.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.flea.market.cart.data.local.entity.CartProductDetailsEntity
import com.flea.market.cart.data.repository.CartRepository
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
import com.flea.market.common.contract.viewmodel.ViewModelContract
import com.flea.market.common.contract.viewmodel.ViewModelContract.Companion.startWithFiveSecStopTimeout
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

private const val DELAY_FOR_SIMULATING_LOADING = 1000L
private const val DISCOUNT_AND_TAX_PERCENTAGE = 0.1

internal class CartDetailsViewModel(private val cartRepository: CartRepository) :
    ViewModelContract<CartDetailsUiState, CartDetailsIntent>, ViewModel() {

    override val uiState: StateFlow<CartDetailsUiState> =
        cartRepository.getItemsInCartStream().map { productList ->
            if (productList.isNotEmpty()) {
                Content(
                    productList = productList.map { it.toItemsInCartViewEntity() },
                    priceDetails = getPriceDetails(productList)
                )
            } else {
                Empty
            }
        }.catch { emit(Error(it)) }.onStart { delay(DELAY_FOR_SIMULATING_LOADING) }.stateIn(
            scope = viewModelScope,
            started = startWithFiveSecStopTimeout,
            initialValue = Loading
        )

    override fun processIntent(intent: CartDetailsIntent) {
        viewModelScope.launch {
            when (intent) {
                is DecreaseQuantity -> decreaseQuantity(intent.itemsInCartViewEntity)
                is IncreaseQuantity -> increaseQuantity(intent.itemsInCartViewEntity)
                is RemoveFromCart -> removeFromCart(intent.itemsInCartViewEntity)
            }
        }
    }

    private suspend fun increaseQuantity(itemsInCartViewEntity: ItemsInCartViewEntity) {
        cartRepository.updateQuantity(
            productId = itemsInCartViewEntity.id,
            quantity = itemsInCartViewEntity.quantity.inc()
        )
    }

    private suspend fun removeFromCart(itemsInCartViewEntity: ItemsInCartViewEntity) {
        cartRepository.removeItem(itemsInCartViewEntity.id)
    }

    private suspend fun decreaseQuantity(itemsInCartViewEntity: ItemsInCartViewEntity) {
        val newQuantity = itemsInCartViewEntity.quantity.dec()
        if (newQuantity > 0) {
            cartRepository.updateQuantity(itemsInCartViewEntity.id, newQuantity)
        } else {
            removeFromCart(itemsInCartViewEntity)
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
