package android.app.ecommerce.ui.screen

import androidx.lifecycle.viewmodel.compose.viewModel
import android.app.ecommerce.ui.composable.ProductList
import android.app.ecommerce.viewmodel.HomeViewModel
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = viewModel()
) {
    val products by viewModel.products
    val isLoading by viewModel.isLoading
    Box(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {
        if (isLoading) {
            CircularProgressIndicator()
        } else {
            ProductList(products)
        }
//        ProductList(FakeData.productList)
    }

}

@Composable
@Preview
fun HomeScreenPreview() {
    HomeScreen()
}