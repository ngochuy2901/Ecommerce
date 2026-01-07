package android.app.ecommerce.data.model

import java.time.LocalDateTime

data class SellerProfile(
    val id: Long,
    val userId: Long,

    val shopName: String,
    val shopDescription: String? = null,
    val shopAddress: String? = null,

    val identityNumber: String,

    val status: SellerStatus,

    val createdAt: LocalDateTime?=null,
    val approvedAt: LocalDateTime? = null
)

enum class SellerStatus {
    PENDING,
    APPROVED,
    REJECTED
}