package android.app.ecommerce.viewmodel

import android.app.ecommerce.data.authentication.Auth
import android.app.ecommerce.data.model.SellerProfile
import android.app.ecommerce.data.model.SellerStatus
import android.app.ecommerce.data.repository.SellerProfileRepository
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
//
//class SignUpForSellerViewModel(private val auth: Auth) : ViewModel() {
//
//    private val sellerProfileRepository: SellerProfileRepository = SellerProfileRepository()
//    private val _isLoading = mutableStateOf(false)
//    val isLoading: State<Boolean> = _isLoading
//    private var _isSignUpSuccess = mutableStateOf(false)
//    val isSignUpSuccess = _isSignUpSuccess
//
//    var _signUpError = mutableStateOf<String?>(null)
//    var signUpError: State<String?> = _signUpError
//
//    fun signUpForSeller(
//        shopName: String,
//        shopDescription: String,
//        shopAddress: String,
//        identityNumber: String
//    ) {
//        viewModelScope.launch {
//            _isLoading.value = true
//            _isSignUpSuccess.value = false
//            _signUpError.value = null
//            try {
//                val userInfo = auth.getUserInfo()
//                val sellerProfile =
//                    SellerProfile(
//                        shopName = shopName,
//                        shopDescription = shopDescription,
//                        shopAddress = shopAddress,
//                        identityNumber = identityNumber,
//                        userId = userInfo!!.id!!,
//                        status = SellerStatus.PENDING
//                    )
//                val response = sellerProfileRepository.signUpForSeller(sellerProfile)
//                if (response.isSuccessful) {
//                    // Đăng ký người bán thành công
//                    _isSignUpSuccess.value = true
//                } else {
//                    // Đăng ký người bán thất bại
//                    _isSignUpSuccess.value = false
//                }
//            } catch (e: HttpException) {
////                _loginError.value = "Sai tài khoản hoặc mật khẩu"
////                Log.d("LoginVM", loginError.value!!)
//            } catch (e: IOException) {
////                _loginError.value = "Không có kết nối mạng"
////                Log.d("LoginVM", loginError.value!!)
//            } catch (e: Exception) {
////                _loginError.value = "Lỗi không xác định"
////                Log.d("LoginVM", e.message?:"Loi deo xac dinh")
//            } finally {
//                _isLoading.value = false
//            }
//        }
//    }
//}
//
//class SignUpForSellerViewModelFactory(private val auth: Auth) : ViewModelProvider.Factory {
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        if (modelClass.isAssignableFrom(SignUpForSellerViewModel::class.java)) {
//            @Suppress("UNCHECKED_CAST")
//            return SignUpForSellerViewModel(auth) as T
//        }
//        throw IllegalArgumentException("Unknown ViewModel class")
//    }
//}

class SignUpForSellerViewModel(
    private val auth: Auth,
    private val sellerProfileRepository: SellerProfileRepository
) : ViewModel() {

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    private val _isSignUpSuccess = mutableStateOf(false)
    val isSignUpSuccess: State<Boolean> = _isSignUpSuccess

    private val _signUpError = mutableStateOf<String?>(null)
    val signUpError: State<String?> = _signUpError

    fun signUpForSeller(
        shopName: String,
        shopDescription: String,
        shopAddress: String,
        identityNumber: String
    ) {
        viewModelScope.launch {
            _isLoading.value = true
            _isSignUpSuccess.value = false
            _signUpError.value = null

            try {
                val userInfo = auth.getUserInfo()
                    ?: throw IllegalStateException("User chưa đăng nhập")

                val userId = userInfo.id
                    ?: throw IllegalStateException("UserId null")

                val sellerProfile = SellerProfile(
                    shopName = shopName,
                    shopDescription = shopDescription,
                    shopAddress = shopAddress,
                    identityNumber = identityNumber,
                    userId = userId,
                    status = SellerStatus.PENDING
                )

                val response = sellerProfileRepository.signUpForSeller(sellerProfile)

                if (response.isSuccessful) {
                    _isSignUpSuccess.value = true
                } else {
                    _signUpError.value =
                        response.errorBody()?.string() ?: "Đăng ký người bán thất bại"
                }

            } catch (e: HttpException) {
                _signUpError.value = "Lỗi server (${e.code()})"
                Log.e("SellerSignUpVM", "HttpException", e)

            } catch (e: IOException) {
                _signUpError.value = "Không có kết nối mạng"
                Log.e("SellerSignUpVM", "IOException", e)

            } catch (e: Exception) {
                _signUpError.value = e.message ?: "Lỗi không xác định"
                Log.e("SellerSignUpVM", "Exception", e)

            } finally {
                _isLoading.value = false
            }
        }
    }
}

class SignUpForSellerViewModelFactory(
    private val auth: Auth
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SignUpForSellerViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SignUpForSellerViewModel(
                auth = auth,
                sellerProfileRepository = SellerProfileRepository()
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
