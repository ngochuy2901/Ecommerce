package android.app.ecommerce.data.model

import java.time.LocalDateTime

data class Review(
    val id: Long,
    val userId: Long,
    val productId: Long,
    val rating: Int,
    val comment: String,
    val createdAt: String
)