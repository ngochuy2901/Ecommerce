package android.app.ecommerce.data.fakedata

import android.app.ecommerce.data.dto.UserDto
import android.app.ecommerce.data.model.Gender
import android.app.ecommerce.data.model.Role
import android.app.ecommerce.data.model.User
import android.app.ecommerce.data.model.UserStatus
import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.LocalDateTime

object UserFakeData {
    @RequiresApi(Build.VERSION_CODES.O)
    val fullUser = User(
        id = 101L,
        username = "admin_super",
        email = "admin@ecommerce.com",
        phone = "0901234567",
        password = "hashed_password_123",
        fullName = "Admin Hệ Thống",
        avatarUrl = "https://example.com/avatars/admin_super.jpg",
        gender = Gender.MALE,
        dateOfBirth = LocalDate.now().toString(),
        role = Role.ADMIN, // Ghi đè giá trị mặc định
        status = UserStatus.ACTIVE,
        lastLogin = LocalDateTime.now().toString(),
        createdAt = LocalDateTime.now().toString()
    )
    @RequiresApi(Build.VERSION_CODES.O)
    val userDtoExample = UserDto(
        id = 25L, // ID của người dùng
        username = "Hiện lên khi không load dược user",
        email = "linh.nguyen@test.com",
        phone = "0987654321", // Ví dụ có số điện thoại
        fullName = "Nguyễn Thu Linh",
        avatarUrl = "https://api.ecommerce.com/avatars/25.png",
        gender = Gender.FEMALE, // Bắt buộc
        dateOfBirth = "29/1/2001",
        role = Role.USER //
    )
}