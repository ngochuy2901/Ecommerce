package android.app.ecommerce.viewmodel.seller

import android.app.ecommerce.data.model.Product
import android.app.ecommerce.data.repository.ProductRepository
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ProductManagementViewModel(
    private val productRepository: ProductRepository
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
                _products.value = productRepository.getProductsByShop()?: emptyList()
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                _isLoading.value = false
            }
        }
    }
}