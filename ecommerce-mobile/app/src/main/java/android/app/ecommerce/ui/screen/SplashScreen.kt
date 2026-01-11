package android.app.ecommerce.ui.screen

import android.app.ecommerce.data.authentication.Auth
import android.window.SplashScreen
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.delay


@Composable
fun SplashScreen(navController: NavController) {
    val auth = Auth(LocalContext.current)

    LaunchedEffect(Unit) {
        delay(2000)

        if (auth.isLoggedIn()) {
            try {

                auth.loadUserProfile()
                navController.navigate("home") {
                    popUpTo("splash") { inclusive = true }
                }
            } catch (e: Exception) {
                auth.logout()
                navController.navigate("entry_auth") {
                    popUpTo("splash") { inclusive = true }
                }
            }
        } else {
            navController.navigate("entry_auth") {
                popUpTo("splash") { inclusive = true }
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF9775FA)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Laza",
            color = Color.White,
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    SplashScreen(rememberNavController())
}