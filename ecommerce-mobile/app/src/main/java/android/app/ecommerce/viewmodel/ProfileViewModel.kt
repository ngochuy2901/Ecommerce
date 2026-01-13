package android.app.ecommerce.viewmodel

import android.app.ecommerce.data.dto.UserDto
import android.app.ecommerce.data.repository.UserRepository
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ProfileViewModel(
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val userRepository: UserRepository = UserRepository()
    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading
    private val _userProfile = mutableStateOf<UserDto?>(null)
    val userProfile: State<UserDto?> = _userProfile

    init {
        loadUserProfile()
    }

    private fun loadUserProfile() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                _userProfile.value = userRepository.getUserProfile()
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                _isLoading.value = false
            }
        }
    }
}