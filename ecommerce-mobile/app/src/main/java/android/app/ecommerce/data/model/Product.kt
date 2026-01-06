package android.app.ecommerce.data.model


data class Product(
    val id: Long,
    val sellerId: Long,
    val categoryId: Long? = null,
    val name: String,
    val description: String? = null,
    val price: Double,
    val salePrice: Double? = null,
    val stock: Int,
    val sku: String? = null,
    val thumbnail: String? = null,
    val status: ProductStatus,
    val createdAt: String? = null,  // hoặc LocalDateTime nếu xử lý JSON tốt
    val updatedAt: String? = null
) {
    enum class ProductStatus {
        ACTIVE,
        OUT_OF_STOCK,
        HIDDEN
    }
}