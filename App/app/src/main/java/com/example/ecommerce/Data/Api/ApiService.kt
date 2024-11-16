package com.example.ecommerce.Data.Api

import com.example.ecommerce.Data.Entity.City
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("api/cities")
    fun getCities():  Call<List<City>>
}