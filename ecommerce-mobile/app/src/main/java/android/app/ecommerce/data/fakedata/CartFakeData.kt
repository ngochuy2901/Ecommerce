package android.app.ecommerce.data.fakedata

import android.app.ecommerce.data.model.Cart
import android.app.ecommerce.data.model.CartItem
import android.app.ecommerce.data.model.Product

object CartFakeData {
    val cartList = List(10) { index ->
        Cart(
            id = index.toLong() + 1,
            userId = index.toLong() + 100
        )
    }

    val cart = Cart(
        id = 1,
        userId = 100
    )

    val cartItem = CartItem(
        id = 1,
        cart = Cart(
            id = 1,
            userId = 100
        ),
        product = Product(
            id = 1,
            sellerId = 10,
            categoryId = 2,
            name = "Áo thun nam",
            description = "Áo thun cotton 100%",
            price = 199_000.0,
            salePrice = 159_000.0,
            stock = 50,
            sku = "TSHIRT-001",
            thumbnail = "https://example.com/tshirt.jpg",
            status = Product.ProductStatus.ACTIVE,
            createdAt = "2025-01-01T10:00:00",
            updatedAt = "2025-01-05T10:00:00"
        ),
        quantity = 2
    )


    val cartItemList = List(10) { index ->
        CartItem(
            id = index.toLong() + 1,
            cart = cart,
            product = Product(
                id = index.toLong() + 1,
                sellerId = 1,
                categoryId = (index % 3 + 1).toLong(),
                name = "Product ${index + 1}",
                description = "Description for product ${index + 1}",
                price = 100_000.0 + index * 10_000,
                salePrice = if (index % 2 == 0) 90_000.0 + index * 8_000 else null,
                stock = 50 - index,
                sku = "SKU-${1000 + index}",
                thumbnail = "https://example.com/product_${index + 1}.jpg",
                status = if (index % 5 == 0)
                    Product.ProductStatus.OUT_OF_STOCK
                else
                    Product.ProductStatus.ACTIVE,
                createdAt = "2025-01-01T10:00:00",
                updatedAt = "2025-01-10T10:00:00"
            ),
            quantity = index % 3 + 1
        )
    }


}