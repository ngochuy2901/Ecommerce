package android.app.ecommerce.data.api

import android.app.ecommerce.data.model.Product
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductApi {
    @GET("product/get_all_products")
    suspend fun getProducts(): Response<List<Product>>

    @GET("product/get_product_by_id/{product_id}")
    suspend fun getProductById(@Path("product_id") productId: Long): Response<Product>

    @GET("product/get_products_by_shop")
    suspend fun getProductsByShop() : Response<List<Product>>
}