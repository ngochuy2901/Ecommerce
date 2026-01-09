package android.app.ecommerce.ui.screen


import android.app.ecommerce.ui.component.LayoutWithHeaderBackIconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun CartScreen(navController: NavController) {
    LayoutWithHeaderBackIconButton("Cart") {

    }
}

@Composable
@Preview
fun CartScreenPreview() {
    CartScreen(rememberNavController())
}