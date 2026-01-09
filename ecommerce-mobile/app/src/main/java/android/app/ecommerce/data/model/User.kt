package android.app.ecommerce.data.model

import java.time.LocalDate
import java.time.LocalDateTime

data class User(
    val id: Long? = null,
    val username: String,
    val email: String,
    val phone: String? = null, // Có thể null
    val password: String? = null,
    val fullName: String? = null,
    val avatarUrl: String? = null,
    val gender: Gender? = null,
    val dateOfBirth: LocalDate? = null,
    val role: Role? = Role.USER,
    val status: UserStatus? = UserStatus.ACTIVE,
    val lastLogin: LocalDateTime? = null,
    val createdAt: LocalDateTime? = null,
    val updatedAt: LocalDateTime? = null
)

enum class Role {
    USER,
    SELLER,
    ADMIN
}

enum class UserStatus {
    ACTIVE,
    BLOCKED
}
enum class Gender {
    MALE,
    FEMALE,
    OTHER
}