package android.app.ecommerce.data.repository

import android.app.ecommerce.data.api.RetrofitClient
import android.app.ecommerce.data.authentication.Auth
import android.app.ecommerce.data.dto.LoginRequest
import android.app.ecommerce.data.dto.LoginResponse
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException


class UserRepository() {
    suspend fun login(loginRequest: LoginRequest) : LoginResponse {
        return RetrofitClient.userApi.login(loginRequest)
    }

}