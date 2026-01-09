package android.app.ecommerce.data.api

import android.app.ecommerce.data.dto.LoginRequest
import android.app.ecommerce.data.dto.LoginResponse
import android.app.ecommerce.data.model.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface UserApi {
    @POST("user/register")
    suspend fun register(@Body user : User) : Response<User>

    @POST("user/login")
    suspend fun login(@Body loginRequest: LoginRequest) : LoginResponse
}