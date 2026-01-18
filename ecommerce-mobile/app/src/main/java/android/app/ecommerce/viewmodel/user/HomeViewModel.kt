package android.app.ecommerce.viewmodel.user

import android.app.ecommerce.data.model.Product
import android.app.ecommerce.data.repository.ProductRepository
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch


class HomeViewModel(
    private val repository: ProductRepository = ProductRepository()
) : ViewModel() {

    private val _products = mutableStateOf<List<Product>>(emptyList())
    val products: State<List<Product>> = _products

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    init {
        fetchProducts()
    }

    private fun fetchProducts() {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                _products.value = repository.getProducts()
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                _isLoading.value = false
            }
        }
    }
}