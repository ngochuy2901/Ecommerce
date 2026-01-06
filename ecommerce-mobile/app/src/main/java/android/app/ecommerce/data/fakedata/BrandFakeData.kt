package android.app.ecommerce.data.fakedata

import android.app.ecommerce.data.model.Brand

object BrandFakeData {
    val brand = Brand(
        id = 1L,
        name = "Nike",
        logo = "https://example.com/images/nike.png",
        description = "Thương hiệu giày thể thao nổi tiếng thế giới."
    )
    val brandList = listOf(
        Brand(
            id = 1L,
            name = "Nike",
            logo = "https://example.com/images/nike.png",
            description = "Thương hiệu giày thể thao nổi tiếng thế giới."
        ),
        Brand(
            id = 2L,
            name = "Adidas",
            logo = "https://example.com/images/adidas.png",
            description = "Thể thao và phong cách đường phố."
        ),
        Brand(
            id = 3L,
            name = "Puma",
            logo = "https://example.com/images/puma.png",
            description = "Giày dép, quần áo thể thao chất lượng cao."
        ),
        Brand(
            id = 4L,
            name = "Apple",
            logo = "https://example.com/images/apple.png",
            description = "Công nghệ, điện thoại, laptop và phụ kiện."
        ),
        Brand(
            id = 5L,
            name = "Samsung",
            logo = "https://example.com/images/samsung.png",
            description = "Điện tử tiêu dùng, điện thoại và TV."
        ),
        Brand(
            id = 6L,
            name = "Sony",
            logo = "https://example.com/images/sony.png",
            description = "Điện tử, âm thanh và hình ảnh chất lượng cao."
        ),
        Brand(
            id = 7L,
            name = "Gucci",
            logo = "https://example.com/images/gucci.png",
            description = "Thời trang xa xỉ và phụ kiện cao cấp."
        ),
    )
}