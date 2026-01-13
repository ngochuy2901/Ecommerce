package android.app.ecommerce.data.repository

import android.app.ecommerce.data.api.RetrofitClient
import android.app.ecommerce.data.model.Cart
import android.app.ecommerce.data.model.CartItem
import android.app.ecommerce.data.model.Product

class CartRepository {
    suspend fun getUserCart(): Cart? {
        val response = RetrofitClient.cartApi.getUserCart()
        return if (response.isSuccessful) {
            response.body()
        } else {
            null
        }
    }
    //add product to cart
    suspend fun addProductToCart(product: Product) : CartItem? {
        val response = RetrofitClient.cartApi.addProductToCart(product)
        return if (response.isSuccessful) {
            response.body()
        } else {
            null
        }
    }

    suspend fun getUserCartItemList() : List<CartItem>? {
        val  response = RetrofitClient.cartApi.getUserCartItemList()
        return if (response.isSuccessful) {
            response.body()
        } else {
            null
        }
    }
}