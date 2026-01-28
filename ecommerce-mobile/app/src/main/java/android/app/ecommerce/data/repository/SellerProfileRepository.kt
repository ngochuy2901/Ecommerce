package android.app.ecommerce.data.repository

import android.app.ecommerce.data.api.RetrofitClient
import android.app.ecommerce.data.model.SellerProfile
import retrofit2.Response

class SellerProfileRepository() {
    suspend fun signUpForSeller(sellerProfile: SellerProfile): Response<SellerProfile> {
        return RetrofitClient.sellerProfileApi.signUpForSeller(sellerProfile)
    }

    suspend fun getSellerProfile(): Response<SellerProfile> {
        return RetrofitClient.sellerProfileApi.getSellerProfile()
    }
}