package android.app.ecommerce.viewmodel

import android.app.ecommerce.data.authentication.Auth
import android.app.ecommerce.data.dto.LoginRequest
import android.content.Context
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class LoginViewModel(private val auth: Auth) : ViewModel() {

    var isLoading = mutableStateOf(false)
        private set
    var isLoginSuccess = mutableStateOf(false)
        private set
    var loginError = mutableStateOf<String?>(null)
        private set

    fun login(email: String, password: String) {
        viewModelScope.launch {
            isLoading.value = true
            loginError.value = null
            try {
                val response = auth.login(LoginRequest(email, password))
                if (response != null) {
                    // Login thành công
                    Log.d("LoginVM", "Login thành công, token: ${response.token}")
                    isLoginSuccess.value = true
                } else {
                    loginError.value = "Email hoặc mật khẩu không đúng"
                }
            } catch (e: Exception) {
                loginError.value = "Lỗi mạng hoặc server"
            } finally {
                isLoading.value = false
            }
        }
    }

    fun logout() {
        auth.logout()
    }

    fun isLoggedIn() = auth.isLoggedIn()
}


//class LoginViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
//            @Suppress("UNCHECKED_CAST")
//            return LoginViewModel(Auth.getInstance(context)) as T
//        }
//        throw IllegalArgumentException("Unknown ViewModel class")
//    }
//}