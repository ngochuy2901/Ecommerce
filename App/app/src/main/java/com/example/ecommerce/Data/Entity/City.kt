package com.example.ecommerce.Data.Entity


import java.io.Serializable

data class City(
    val cityId: Int,
    val cityName: String,
    val cityType: String
) : Serializable
