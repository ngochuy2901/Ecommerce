package android.app.ecommerce.ui.component

import android.app.ecommerce.ui.screen.user.CartScreen
import android.app.ecommerce.ui.screen.authentication.EntryAuthScreen
import android.app.ecommerce.ui.screen.user.HomeScreen
import android.app.ecommerce.ui.screen.authentication.LoginScreen
import android.app.ecommerce.ui.screen.user.ProductDetailScreen
import android.app.ecommerce.ui.screen.user.ProfileScreen
import android.app.ecommerce.ui.screen.user.ReviewScreen
import android.app.ecommerce.ui.screen.seller.SellerHomeScreen
import android.app.ecommerce.ui.screen.seller.SignUpForSellerScreen
import android.app.ecommerce.ui.screen.authentication.SignUpScreen
import android.app.ecommerce.ui.screen.authentication.SplashScreen
import android.app.ecommerce.ui.screen.authentication.VerificationCodeScreen
import android.app.ecommerce.viewmodel.user.ProductDetailViewModel
import android.app.ecommerce.viewmodel.user.ProductDetailViewModelFactory
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "splash"
    ) {
        composable("home") {
            HomeScreen(navController = navController)
        }
        composable("login") {
            LoginScreen(navController = navController)
        }
        composable("review") {
            ReviewScreen(navController = navController)
        }
        composable("sign_up") {
            SignUpScreen(navController = navController)
        }
        composable("cart") {
            CartScreen(navController)
        }
        composable("profile") {
            ProfileScreen(navController)
        }
        composable("dashboard") {
            SellerHomeScreen(navController)
        }
        composable("sign_up_seller") {
            SignUpForSellerScreen(navController)
        }
        composable("splash") {
            SplashScreen(navController = navController)
        }
        composable("verification_code") {
            VerificationCodeScreen(navController = navController)
        }
        composable("cart") { CartScreen(navController) }
        composable("entry_auth") {
            EntryAuthScreen(navController = navController)
        }
        composable(
            "product_detail/{productId}",
            arguments = listOf(navArgument("productId") { type = NavType.LongType })
        ) { navBackStackEntry ->
            val viewModel: ProductDetailViewModel = viewModel(
                factory = ProductDetailViewModelFactory(navBackStackEntry.savedStateHandle)
            )
            ProductDetailScreen(navController, viewModel)
        }
    }
}
