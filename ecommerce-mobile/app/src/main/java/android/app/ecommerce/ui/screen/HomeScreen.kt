package android.app.ecommerce.ui.screen

import android.app.ecommerce.R
import android.app.ecommerce.ui.component.AppNavigationDrawer
import android.app.ecommerce.ui.component.DrawerMenuIcon
import androidx.lifecycle.viewmodel.compose.viewModel
import android.app.ecommerce.ui.component.ProductList
import android.app.ecommerce.ui.component.SearchBar
import android.app.ecommerce.viewmodel.HomeViewModel
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = viewModel()
) {
    val products by viewModel.products
    val isLoading by viewModel.isLoading

    val drawerState = rememberDrawerState(DrawerValue.Closed)

    AppNavigationDrawer(drawerState = drawerState) {
        Scaffold(
            topBar = {
                TopAppBar(
                    navigationIcon = {
                        DrawerMenuIcon(drawerState)
                    },
                    title = {}
                )
            }
        ) { padding ->
            Column(modifier = Modifier.padding(padding)) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Text("Hello", fontSize = 28.sp, fontWeight = FontWeight.SemiBold)
                    Text("Welcome to Laza.", fontSize = 15.sp, color = Color(0xFF8F959E))
                    SearchBar(modifier = Modifier.fillMaxWidth())
                    if (isLoading) {
                        CircularProgressIndicator()
                    } else {
                        ProductList(products)
                    }
//        ProductList(FakeData.productList)
                }
            }
        }
    }


}

@Composable
@Preview
fun HomeScreenPreview() {
    HomeScreen()
}

