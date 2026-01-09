package android.app.ecommerce.data.model

data class Cart(
    val id: Long? = null, // Cho phép null khi Cart chưa được lưu (chưa có ID)
    // Tương đương với @OneToOne @JoinColumn(name = "user_id")
    val userId: Long,

    // Tùy chọn: Nếu API trả về đối tượng User đầy đủ
    val user: User? = null
)