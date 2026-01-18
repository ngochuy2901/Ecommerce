package android.app.ecommerce.viewmodel.user

import android.app.ecommerce.data.authentication.Auth
import android.app.ecommerce.data.dto.UserDto
import android.app.ecommerce.data.repository.UserRepository
import android.os.Bundle
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.savedstate.SavedStateRegistryOwner
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val auth: Auth,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing = _isRefreshing.asStateFlow()
    private val userRepository: UserRepository = UserRepository()
    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading
    private val _userProfile = mutableStateOf<UserDto?>(null)
    val userProfile: State<UserDto?> = _userProfile

    init {
        viewModelScope.launch {
            loadUserProfile()
        }
    }

    fun refresh() {
        viewModelScope.launch {
            _isRefreshing.value = true
            auth.loadUserProfile()
            loadUserProfile()
            _isRefreshing.value = false
        }
    }

    private suspend fun loadUserProfile() {
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

class ProfileViewModelFactory(
    private val auth: Auth,
    owner: SavedStateRegistryOwner,
    defaultArgs: Bundle? = null
) : AbstractSavedStateViewModelFactory(owner, defaultArgs) {

    override fun <T : ViewModel> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T {
        if (modelClass.isAssignableFrom(ProfileViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ProfileViewModel(auth, handle) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
