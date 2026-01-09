package android.app.ecommerce.data.model

data class CartItem(
    val id: Long? = null, // Sử dụng 'val' (read-only) và '? = null' để cho phép giá trị null (ví dụ: khi tạo mới chưa có ID)
    // Nếu đối tượng Cart và Product quá lớn, trong môi hình Android thường sẽ chỉ dùng ID:
    // val cartId: Long,
    // val productId: Long,

    val cart: Cart? = null, // Tùy chọn: Nếu bạn cần toàn bộ đối tượng Cart
    val product: Product? = null, // Tùy chọn: Nếu bạn cần toàn bộ đối tượng Product
    val quantity: Int
)