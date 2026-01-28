package android.app.ecommerce.data.api

import android.app.ecommerce.data.model.SellerProfile
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface SellerProfileApi {
    @POST("seller_profile/sign_up_for_seller")
    suspend fun signUpForSeller(@Body sellerProfile: SellerProfile) : Response<SellerProfile>

    @GET("seller_profile/get_seller_profile_info")
    suspend fun getSellerProfile() : Response<SellerProfile>
}