package android.app.ecommerce.data.fakedata

import android.app.ecommerce.data.model.Review

object ReviewFakeData {
    val review = Review(1, 1, 101, 5, "Sản phẩm rất tốt", "2025-01-01T10:00:00")
    val reviewList = listOf(
        Review(1, 1, 101, 5, "Sản phẩm rất tốt", "2025-01-01T10:00:00"),
        Review(2, 2, 101, 4, "Dùng ổn trong tầm giá", "2025-01-02T11:00:00"),
        Review(3, 3, 101, 3, "Bình thường", "2025-01-03T12:00:00"),
        Review(4, 4, 101, 5, "Rất đáng tiền", "2025-01-04T13:00:00"),
        Review(5, 5, 101, 2, "Chất lượng chưa tốt", "2025-01-05T14:00:00"),
        Review(6, 6, 101, 4, "Giao hàng nhanh", "2025-01-06T15:00:00"),
        Review(7, 7, 101, 5, "Sẽ ủng hộ lần sau", "2025-01-07T16:00:00"),
        Review(8, 8, 101, 3, "Tạm được", "2025-01-08T17:00:00"),
        Review(9, 9, 101, 4, "Đóng gói cẩn thận", "2025-01-09T18:00:00"),
        Review(10, 10, 101, 1, "Không hài lòng", "2025-01-10T19:00:00")
    )
}