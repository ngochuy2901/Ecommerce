package android.app.ecommerce.ui.screen.user

import android.app.ecommerce.data.authentication.Auth
import android.app.ecommerce.ui.component.LayoutWithHeaderBackIconButton
import android.app.ecommerce.viewmodel.user.ProfileViewModel
import android.app.ecommerce.viewmodel.user.ProfileViewModelFactory
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.savedstate.compose.LocalSavedStateRegistryOwner

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(navController: NavController) {

    val context = LocalContext.current
    val auth = Auth(context)

    val viewModel: ProfileViewModel = viewModel(
        factory = ProfileViewModelFactory(
            auth = auth,
            owner = LocalSavedStateRegistryOwner.current
        )
    )

    val userProfile by viewModel.userProfile
    val isLoading by viewModel.isLoading
    val isRefreshing by viewModel.isRefreshing.collectAsState()

    PullToRefreshBox(
        isRefreshing = isRefreshing,
        onRefresh = { viewModel.refresh() }
    ) {
        LayoutWithHeaderBackIconButton(
            title = "Profile",
            onBackClick = { navController.popBackStack() }
        ) {
            Text(userProfile?.username ?: "Loading...")
        }
    }
}
@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    LayoutWithHeaderBackIconButton("Profile", {}) {
        Text("preview_username")
    }
}
