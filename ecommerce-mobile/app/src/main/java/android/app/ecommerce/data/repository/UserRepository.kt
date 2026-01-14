package android.app.ecommerce.data.repository

import android.app.ecommerce.data.api.RetrofitClient
import android.app.ecommerce.data.authentication.Auth
import android.app.ecommerce.data.dto.LoginRequest
import android.app.ecommerce.data.dto.LoginResponse
import android.app.ecommerce.data.dto.UserDto
import android.app.ecommerce.data.model.User
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

    suspend fun getUserProfile() : UserDto {
        return RetrofitClient.userApi.getUserProfile()
    }

    suspend fun register(user: User) : Response<UserDto> {
        return RetrofitClient.userApi.register(user);
    }
}