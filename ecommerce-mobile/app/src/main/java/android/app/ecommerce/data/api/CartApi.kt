package android.app.ecommerce.data.api

import android.app.ecommerce.data.model.Cart
import android.app.ecommerce.data.model.CartItem
import android.app.ecommerce.data.model.Product
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface CartApi {
    @GET("cart/get_user_cart")
    suspend fun getUserCart(): Response<Cart>
    //add product to cart
    @POST("cart/add_product_to_cart")
    suspend fun addProductToCart(@Body product: Product): Response<CartItem>

    @GET("cart/get_user_cart_item_list")
    suspend fun getUserCartItemList() : Response<List<CartItem>>
}