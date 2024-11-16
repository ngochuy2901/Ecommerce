package com.example.ecommerce.Data.Api

import com.example.ecommerce.Data.Entity.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface UserService {
    @POST("user/adduser")
    fun createNewUser(@Body user: User): Call<User>
}