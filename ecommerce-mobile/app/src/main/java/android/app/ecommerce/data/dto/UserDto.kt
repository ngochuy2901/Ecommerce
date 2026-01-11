package android.app.ecommerce.data.dto

import android.app.ecommerce.data.model.Gender
import android.app.ecommerce.data.model.Role
import java.time.LocalDate

data class UserDto(
    val id: Long?,
    val username: String,
    val email: String,
    val phone: String?,
    val fullName: String,
    val avatarUrl: String?,
    val gender: Gender,
    val dateOfBirth: String,
    val role: Role
)