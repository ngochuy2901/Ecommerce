package android.app.ecommerce.data.fakedata

import android.app.ecommerce.data.model.Product

object FakeData {
    val product = Product(
        id = 2L,
        sellerId = 102L,
        categoryId = 11L,
        name = "Quần Jean Nữ",
        description = "Quần jean co giãn, ôm dáng",
        price = 299000.0,
        salePrice = 249000.0,
        stock = 15,
        sku = "QJN002",
        thumbnail = "https://example.com/images/quan1.png",
        status = Product.ProductStatus.ACTIVE,
        createdAt = "2026-01-06T10:05:00",
        updatedAt = "2026-01-06T10:05:00"
    )
    val productList = listOf(
        Product(
            id = 1L,
            sellerId = 101L,
            categoryId = 10L,
            name = "Áo Thun Nam",
            description = "Áo thun cotton 100%, mềm mại, co giãn",
            price = 199000.0,
            salePrice = 149000.0,
            stock = 20,
            sku = "ATN001",
            thumbnail = "https://example.com/images/ao1.png",
            status = Product.ProductStatus.ACTIVE,
            createdAt = "2026-01-06T10:00:00",
            updatedAt = "2026-01-06T10:00:00"
        ),
        Product(
            id = 2L,
            sellerId = 102L,
            categoryId = 11L,
            name = "Quần Jean Nữ",
            description = "Quần jean co giãn, ôm dáng",
            price = 299000.0,
            salePrice = 249000.0,
            stock = 15,
            sku = "QJN002",
            thumbnail = "https://example.com/images/quan1.png",
            status = Product.ProductStatus.ACTIVE,
            createdAt = "2026-01-06T10:05:00",
            updatedAt = "2026-01-06T10:05:00"
        ),
        Product(
            id = 3L,
            sellerId = 103L,
            categoryId = 12L,
            name = "Giày Thể Thao",
            description = "Giày sneaker unisex, thoáng khí",
            price = 599000.0,
            salePrice = null,
            stock = 10,
            sku = "GTT003",
            thumbnail = "https://example.com/images/giay1.png",
            status = Product.ProductStatus.OUT_OF_STOCK,
            createdAt = "2026-01-06T10:10:00",
            updatedAt = "2026-01-06T10:10:00"
        ),
        Product(
            id = 4L,
            sellerId = 104L,
            categoryId = 13L,
            name = "Túi Xách Nữ",
            description = "Túi xách da tổng hợp, nhiều ngăn",
            price = 399000.0,
            salePrice = 349000.0,
            stock = 5,
            sku = "TXN004",
            thumbnail = "https://example.com/images/tui1.png",
            status = Product.ProductStatus.ACTIVE,
            createdAt = "2026-01-06T10:15:00",
            updatedAt = "2026-01-06T10:15:00"
        ),
        Product(
            id = 5L,
            sellerId = 105L,
            categoryId = 14L,
            name = "Đồng Hồ Nam",
            description = "Đồng hồ dây thép không gỉ, chống nước 5ATM",
            price = 1299000.0,
            salePrice = 1199000.0,
            stock = 8,
            sku = "DNH005",
            thumbnail = "https://example.com/images/dongho1.png",
            status = Product.ProductStatus.HIDDEN,
            createdAt = "2026-01-06T10:20:00",
            updatedAt = "2026-01-06T10:20:00"
        )
    )
}