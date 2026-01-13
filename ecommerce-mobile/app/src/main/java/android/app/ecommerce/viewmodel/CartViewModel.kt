package android.app.ecommerce.viewmodel

import android.app.ecommerce.data.model.Cart
import android.app.ecommerce.data.model.CartItem
import android.app.ecommerce.data.repository.CartRepository
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class CartViewModel(
    private val cartRepository: CartRepository = CartRepository()
) : ViewModel() {
    private val _cart = mutableStateOf<Cart?>(null)
    val cart: State<Cart?> = _cart


    private val _listCartItem = mutableStateOf<List<CartItem>>(emptyList())
    val listCartItem: State<List<CartItem>> = _listCartItem


    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    init {
        loadUserCart()
    }

    private fun loadUserCart() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                _listCartItem.value = cartRepository.getUserCartItemList()?: emptyList()
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                _isLoading.value = false
                _cart.value = cartRepository.getUserCart()
            }
        }
    }
}