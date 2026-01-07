package android.app.ecommerce.data.fakedata

import android.app.ecommerce.data.model.SellerProfile
import android.app.ecommerce.data.model.SellerStatus
import java.time.LocalDateTime

object SellerProfileFakeData {
    val sellerProfile = SellerProfile(
        id = 1L,
        userId = 10L,
        shopName = "Huy Store",
        shopDescription = "Chuyên đồ công nghệ",
        shopAddress = "Hà Nội",
        identityNumber = "012345678901",
        status = SellerStatus.PENDING,
        createdAt = null,
        approvedAt = null
    )

}