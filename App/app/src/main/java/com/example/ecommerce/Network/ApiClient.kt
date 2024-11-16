package com.example.ecommerce.Network

import android.content.Context
import android.provider.Settings.System.getString
import android.util.Log
import com.example.ecommerce.Data.Api.ApiService
import com.example.ecommerce.Data.Api.UserService
import com.example.ecommerce.R
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient() {
    private val BASE_URL = "http://172.19.200.164"

    val apiService: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
    val userService: UserService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UserService::class.java)
    }
}
