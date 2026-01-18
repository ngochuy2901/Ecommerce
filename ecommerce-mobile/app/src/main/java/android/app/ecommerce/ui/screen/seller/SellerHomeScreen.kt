package android.app.ecommerce.ui.screen.seller

import android.app.ecommerce.ui.component.AppNavigationDrawer
import android.app.ecommerce.ui.component.DrawerMenuIcon
import android.app.ecommerce.ui.component.SearchBar
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SellerHomeScreen(navController: NavController) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)

    AppNavigationDrawer(
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
                    Text("Hello Shop", fontSize = 28.sp, fontWeight = FontWeight.SemiBold)
                    Text("Welcome to Laza.", fontSize = 15.sp, color = Color(0xFF8F959E))
                    SearchBar(modifier = Modifier.fillMaxWidth())
//                    if (isLoading) {
//                        CircularProgressIndicator()
//                    } else {
//                        ProductList(products, navController)
//                    }
                }
            }
        }
    }
}

@Composable
@Preview
fun SellerHomeScreenPreview() {
    SellerHomeScreen(rememberNavController())
}