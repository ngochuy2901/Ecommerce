package android.app.ecommerce.viewmodel.auth

import android.app.ecommerce.data.authentication.Auth
import android.app.ecommerce.data.dto.LoginRequest
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class LoginViewModel(private val auth: Auth) : ViewModel() {

    var isLoading = mutableStateOf(false)
    var _isLoginSuccess  = mutableStateOf(false)
    val isLoginSuccess = _isLoginSuccess

    var loginError = mutableStateOf<String?>(null)

    fun login(username: String, password: String) {
        viewModelScope.launch {
            isLoading.value = true
            loginError.value = null
            try {
                val response = auth.login(LoginRequest(username, password))
                Log.d("LoginVM", "Response: ${response!!.message}")
                if (response!!.status == "success") {
                    // Login thành công
                    Log.d("LoginVM", "Login thành công")
                    isLoginSuccess.value = true
                } else {
                    Log.d("LoginVM", "Response: ${response!!.message}")
                    loginError.value = "Email hoặc mật khẩu không đúng"
                }
            } catch (e: HttpException) {
                loginError.value = "Sai tài khoản hoặc mật khẩu"
                Log.d("LoginVM", loginError.value!!)
            } catch (e: IOException) {
                loginError.value = "Không có kết nối mạng"
                Log.d("LoginVM", loginError.value!!)
            } catch (e: Exception) {
                loginError.value = "Lỗi không xác định"
                Log.d("LoginVM", e.message?:"Loi deo xac dinh")
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

class LoginViewModelFactory(
    private val auth: Auth
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return LoginViewModel(auth) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
