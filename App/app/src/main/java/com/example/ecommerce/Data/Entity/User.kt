package com.example.ecommerce.Data.Entity

import java.io.Serializable

class User (
    val userEmail:String,
    val userPhone:String,
    val userPassword:String,
    val userFirstName:String,
    val userLastName:String,
    val userAddress1:String,
    val userAddress2:String
) : Serializable