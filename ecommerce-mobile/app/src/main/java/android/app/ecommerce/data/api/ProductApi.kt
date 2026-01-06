package android.app.ecommerce.data.api

import android.app.ecommerce.data.model.Product
import retrofit2.Response
import retrofit2.http.GET

interface ProductApi {
    @GET("product/get_all_products")
    suspend fun getProducts(): Response<List<Product>>
}