package android.app.ecommerce.ui.screen

import android.app.ecommerce.R
import android.app.ecommerce.data.fakedata.FakeData
import android.app.ecommerce.data.fakedata.SellerProfileFakeData
import android.app.ecommerce.data.model.Product
import android.app.ecommerce.data.repository.CartRepository
import android.app.ecommerce.ui.component.SellerInfo
import android.app.ecommerce.viewmodel.ProductDetailViewModel
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
@Composable
fun ProductDetailScreen(
    navController: NavController,
    viewModel: ProductDetailViewModel
) {
    val product by viewModel.product
    val isLoading by viewModel.isLoading
    val context = LocalContext.current

    Box(modifier = Modifier.fillMaxSize()) {

        if (isLoading) {
            Text("Loading...", modifier = Modifier.align(Alignment.Center))
            return
        }

        if (product == null) {
            Text("Product not found", modifier = Modifier.align(Alignment.Center))
            return
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Box {
                Image(
                    painter = painterResource(R.drawable.ic_launcher_background),
                    contentDescription = null,
                    modifier = Modifier
                        .height(418.dp)
                        .fillMaxWidth(),
                    contentScale = ContentScale.Crop
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    IconButton(
                        onClick = { navController.popBackStack() },
                        modifier = Modifier
                            .size(45.dp)
                            .clip(CircleShape)
                            .background(Color(0xFFF5F6FA))
                    ) {
                        Icon(
                            painterResource(R.drawable.icon_arrow_back),
                            contentDescription = null
                        )
                    }

                    IconButton(
                        onClick = {
                            navController.navigate("cart")
                        },
                        modifier = Modifier
                            .size(45.dp)
                            .clip(CircleShape)
                            .background(Color(0xFFF5F6FA))
                    ) {
                        Icon(
                            painterResource(R.drawable.icon_cart),
                            contentDescription = null
                        )
                    }
                }
            }

            Text("Description", fontWeight = FontWeight.SemiBold, fontSize = 17.sp)
            Text(
                product!!.description?:"",
                fontSize = 15.sp,
                color = Color(0xFF8F959E)
            )

            SellerInfo(SellerProfileFakeData.sellerProfile)
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .align(Alignment.BottomCenter)
                .clip(RoundedCornerShape(12.dp))
                .background(Color(0xFF9775FA))
                .clickable {
                    viewModel.addToCart(product!!)
                    Toast.makeText(context, "Add to cart successfully", Toast.LENGTH_SHORT).show()
                },
            contentAlignment = Alignment.Center
        ) {
            Text("Add to cart", fontSize = 17.sp, color = Color.White)
        }
    }
}


@Composable
@Preview
fun ProductDetailScreenPreview() {
    ProductDetailScreen(rememberNavController(), viewModel ())
}