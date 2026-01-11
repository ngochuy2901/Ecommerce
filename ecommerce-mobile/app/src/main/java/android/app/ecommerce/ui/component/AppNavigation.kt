package android.app.ecommerce.ui.component

import android.app.ecommerce.ui.screen.CartScreen
import android.app.ecommerce.ui.screen.EntryAuthScreen
import android.app.ecommerce.ui.screen.HomeScreen
import android.app.ecommerce.ui.screen.LoginScreen
import android.app.ecommerce.ui.screen.ProductDetailScreen
import android.app.ecommerce.ui.screen.ReviewScreen
import android.app.ecommerce.ui.screen.SignUpScreen
import android.app.ecommerce.ui.screen.SplashScreen
import android.app.ecommerce.ui.screen.VerificationCodeScreen
import android.app.ecommerce.viewmodel.ProductDetailViewModel
import android.app.ecommerce.viewmodel.ProductDetailViewModelFactory
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
