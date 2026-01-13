package android.app.ecommerce.data.model

data class Cart(
    val id: Long? = null,
    val userId: Long,
    val user: User? = null
)