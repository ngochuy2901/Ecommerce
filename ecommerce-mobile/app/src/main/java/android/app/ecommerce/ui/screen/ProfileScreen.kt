package android.app.ecommerce.ui.screen

import android.app.ecommerce.ui.component.LayoutWithHeaderBackIconButton
import android.app.ecommerce.viewmodel.ProfileViewModel
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun ProfileScreen(navController: NavController, viewModel: ProfileViewModel = viewModel()) {
    val userProfile by viewModel.userProfile
    val isLoading by viewModel.isLoading
    LayoutWithHeaderBackIconButton("Profile", { navController.popBackStack() }) {

    }

}

@Composable
@Preview
fun ProfileScreenPreview() {
    ProfileScreen(rememberNavController(), viewModel())
}