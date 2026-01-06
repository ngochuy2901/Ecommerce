package android.app.ecommerce.data.repository

import android.app.ecommerce.data.api.RetrofitClient
import android.app.ecommerce.data.model.Product


class ProductRepository {

    suspend fun getProducts(): List<Product> {
        val response = RetrofitClient.productApi.getProducts()

        return if (response.isSuccessful) {
            response.body() ?: emptyList()
        } else {
            emptyList()
        }
    }
}