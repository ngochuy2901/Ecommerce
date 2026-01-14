package android.app.ecommerce.viewmodel

import android.app.ecommerce.data.authentication.Auth
import android.app.ecommerce.data.model.User
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class SignUpViewModel(private val auth: Auth) : ViewModel() {

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading
    private var _isSignUpSuccess = mutableStateOf(false)
    val isSignUpSuccess = _isSignUpSuccess

    var _loginError = mutableStateOf<String?>(null)
    var loginError : State<String?> = _loginError


    fun signUp(username: String, password: String, email: String) {
        viewModelScope.launch {
            _isLoading.value = true
            _loginError.value = null
            try {
                val user = User(username = username, password = password, email = email)
                val response = auth.signUp(user)
                if (response!=null) {
                    // Login thành công
                    Log.d("LoginVM", "Login thành công")
                    _isSignUpSuccess.value = true
                } else {
                    _isSignUpSuccess.value = false
                }
            } catch (e: HttpException) {
                _loginError.value = "Sai tài khoản hoặc mật khẩu"
                Log.d("LoginVM", loginError.value!!)
            } catch (e: IOException) {
                _loginError.value = "Không có kết nối mạng"
                Log.d("LoginVM", loginError.value!!)
            } catch (e: Exception) {
                _loginError.value = "Lỗi không xác định"
                Log.d("LoginVM", e.message?:"Loi deo xac dinh")
            } finally {
                _isLoading.value = false
            }
        }
    }
}



class SignUpViewModelFactory(private val auth: Auth) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SignUpViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SignUpViewModel(auth) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
