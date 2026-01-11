package android.app.ecommerce.data.authentication

import android.app.ecommerce.data.dto.LoginRequest
import android.app.ecommerce.data.dto.LoginResponse
import android.app.ecommerce.data.dto.UserDto
import android.app.ecommerce.data.model.User
import android.app.ecommerce.data.repository.UserRepository
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.core.content.edit
import com.google.gson.Gson

class Auth(context: Context) {
    private val gson = Gson()
    private val prefs: SharedPreferences =
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    suspend fun login(loginRequest: LoginRequest): LoginResponse? {
        return try {
            val response = UserRepository().login(loginRequest)
            Log.d("AuthLogin", "Response: $response")
            if (!response.token.isNullOrEmpty()) {
                saveToken(response.token)
                return response
            } else {
                Log.d("AuthLogin", "Login thất bại: token null")
                return response
            }

        } catch (e: Exception) {
            Log.e("AuthLogin", "Lỗi login: ${e::class.java} - ${e.message}")
            null
        }
    }
    suspend fun loadUserProfile() {
        val userDto = UserRepository().getUserProfile()
        saveUserInfo(userDto)
    }


    // Lưu token sau khi login
    fun saveToken(token: String) {
        prefs.edit { putString(KEY_TOKEN, token) }
    }



    // Lấy token (nếu có)
    fun getToken(): String? {
        return prefs.getString(KEY_TOKEN, null)
    }

    fun saveUserInfo(userDto: UserDto) {
        val json = gson.toJson(userDto)
        prefs.edit {
            putString("user", json)
        }
    }

    fun getUserInfo(): UserDto? {
        val json = prefs.getString("user", null)
        return json?.let {
            gson.fromJson(it, UserDto::class.java)
        }
    }


    // Kiểm tra đã login chưa
    fun isLoggedIn(): Boolean {
        return !getToken().isNullOrEmpty()
    }

    // Xóa token khi logout
    fun logout() {
        prefs.edit().remove(KEY_TOKEN).apply()
    }
    companion object {
        public const val PREFS_NAME = "auth_prefs"
        private const val KEY_TOKEN = "auth_token"
    }
}
