package android.app.ecommerce.ui.screen.seller

import android.app.ecommerce.data.model.SellerStatus
import android.app.ecommerce.data.repository.SellerProfileRepository
import android.app.ecommerce.ui.component.AppNavigationDrawer
import android.app.ecommerce.ui.component.AppNavigationDrawerForSeller
import android.app.ecommerce.ui.component.DrawerMenuIcon
import android.app.ecommerce.ui.component.SearchBar
import android.app.ecommerce.viewmodel.seller.SellerHomeViewModel
import android.app.ecommerce.viewmodel.seller.SellerHomeViewModelFactory
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SellerHomeScreen(navController: NavController) {
    val repository = remember {
        SellerProfileRepository()
    }
    val viewModel: SellerHomeViewModel = viewModel(
        factory = SellerHomeViewModelFactory(repository)
    )
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val isLoading by viewModel.isLoading
    val sellerProfile by viewModel.sellerProfile

    if (isLoading) {
        CircularProgressIndicator()
    } else {
        when (sellerProfile?.status) {
            SellerStatus.PENDING -> {
                Toast.makeText(LocalContext.current, "Pending", Toast.LENGTH_SHORT).show()
                navController.navigate("home")
            }

            SellerStatus.APPROVED -> {
                AppNavigationDrawerForSeller(
                    drawerState = drawerState,
                    navController = navController
                ) {
                    Scaffold(
                        topBar = {
                            TopAppBar(
                                navigationIcon = {
                                    DrawerMenuIcon(drawerState, navController)
                                },
                                title = {}
                            )
                        }
                    ) { paddingValues ->
                        Column(modifier = Modifier.padding(paddingValues)) {
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(16.dp),
                                verticalArrangement = Arrangement.spacedBy(10.dp)
                            ) {
                                Text(
                                    "Hello Shop",
                                    fontSize = 28.sp,
                                    fontWeight = FontWeight.SemiBold
                                )
                                Text(
                                    "Welcome to Laza.",
                                    fontSize = 15.sp,
                                    color = Color(0xFF8F959E)
                                )
                                SearchBar(modifier = Modifier.fillMaxWidth())
                            }
                        }
                    }
                }
            }

            else -> {
                Toast.makeText(LocalContext.current, "Rejected", Toast.LENGTH_SHORT).show()
                navController.navigate("home")
            }
        }
    }


}

@Composable
@Preview
fun SellerHomeScreenPreview() {
    SellerHomeScreen(rememberNavController())
}