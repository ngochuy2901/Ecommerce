package com.example.ecommerce.Data.Auth

import com.example.ecommerce.Data.Entity.User
import com.example.ecommerce.Network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
class Auth {
    fun createNewUser(user: User): Boolean {
        val apiClient = ApiClient()
        // Tạo một biến flag để theo dõi kết quả của quá trình thêm user
        var isSuccess = false
        // Gọi API để tạo người dùng mới
        apiClient.userService.createNewUser(user).enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.isSuccessful) {
                    // Thành công: trả về true
                    isSuccess = true
                    val createdUser = response.body() // Đối tượng User đã được tạo
                    println("User created successfully: ${createdUser?.userEmail}")
                } else {
                    // Thất bại: trả về false
                    isSuccess = false
                    println("Failed to create user: ${response.message()}")
                }
            }
            override fun onFailure(call: Call<User>, t: Throwable) {
                // Lỗi kết nối hoặc các lỗi không xác định: trả về false
                isSuccess = false
                println("Error: ${t.localizedMessage}")
            }
        })
        // Trả về kết quả thành công hay thất bại (false nếu thất bại, true nếu thành công)
        return isSuccess
    }
}