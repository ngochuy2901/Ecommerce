package android.app.ecommerce.data.model

data class CartItem(
    val id: Long? = null,
    // val cartId: Long,
    // val productId: Long,
    val cart: Cart? = null,
    val product: Product? = null,
    val quantity: Int
)