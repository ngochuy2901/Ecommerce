package android.app.ecommerce.viewmodel.seller

import android.app.ecommerce.data.model.SellerProfile
import android.app.ecommerce.data.repository.SellerProfileRepository
import android.app.ecommerce.data.repository.UserRepository
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class SellerHomeViewModel(
    private val sellerProfileRepository: SellerProfileRepository
) : ViewModel() {
    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    private val _sellerProfile = mutableStateOf<SellerProfile?>(null)
    val sellerProfile: State<SellerProfile?> = _sellerProfile

    init {
        checkUserProfile()
    }

    private fun checkUserProfile() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                _sellerProfile.value = sellerProfileRepository.getSellerProfile().body()
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                _isLoading.value = false
            }
        }
    }
}
class SellerHomeViewModelFactory(
    private val repository: SellerProfileRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SellerHomeViewModel::class.java)) {
            return SellerHomeViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel")
    }
}
