package android.app.ecommerce.ui.screen


import android.app.ecommerce.R
import android.app.ecommerce.data.fakedata.CartFakeData
import android.app.ecommerce.data.model.CartItem
import android.app.ecommerce.ui.component.LayoutWithHeaderBackIconButton
import android.app.ecommerce.ui.component.ProductList
import android.app.ecommerce.viewmodel.CartViewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun CartScreen(
    navController: NavController,
    viewModel: CartViewModel = viewModel()
) {
    val listCartItem by viewModel.listCartItem
    val isLoading by viewModel.isLoading
        Box(modifier = Modifier.fillMaxSize()) {
            LayoutWithHeaderBackIconButton("Cart", {navController.popBackStack()}) {
                if (isLoading) {
                    CircularProgressIndicator()
                } else {
                    ListCartItem(listCartItem)
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .align(Alignment.BottomCenter)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color(0xFF9775FA))
                    .clickable {
                        navController.navigate("sign_up")
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Check out",
                    fontSize = 17.sp,
                    color = Color.White
                )
            }
        }
}

@Composable
@Preview
fun CartScreenPreview() {
    CartScreen(rememberNavController())
}

@Composable
fun ListCartItem(listCartItem: List<CartItem>) {
    Column {
        listCartItem.forEach { cartItem ->
            CartItem(cartItem)
            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}

@Composable
@Preview
fun ListCartItemPreview() {
    ListCartItem(CartFakeData.cartItemList)
}

@Composable
fun CartItem(cartItem: CartItem) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(Color.White)
            .padding(12.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {

            // Product image
            Image(
                painter = painterResource(R.drawable.ic_launcher_background),
                contentDescription = null,
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(10.dp))
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {

                // Product name
                Text(
                    text = cartItem.product?.name ?: "",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 2
                )

                Spacer(modifier = Modifier.height(4.dp))

                // Price
                Text(
                    text = "${cartItem.product?.salePrice ?: cartItem.product?.price} đ",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFF9775FA)
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Quantity + delete
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    QuantityButton(
                        icon = R.drawable.icon_arrow_down,
                        onClick = { }
                    )

                    Text(
                        text = cartItem.quantity.toString(),
                        modifier = Modifier.padding(horizontal = 12.dp),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium
                    )

                    QuantityButton(
                        icon = R.drawable.icon_arrow_up,
                        onClick = { }
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    IconButton(
                        onClick = { },
                        modifier = Modifier.size(36.dp)
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.icon_delete),
                            contentDescription = null,
                            tint = Color(0xFF8F959E)
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun QuantityButton(
    icon: Int,
    onClick: () -> Unit
) {
    IconButton(
        onClick = onClick,
        modifier = Modifier
            .size(36.dp)
            .clip(CircleShape)
            .background(Color(0xFFF5F5F5))
    ) {
        Icon(
            painter = painterResource(icon),
            contentDescription = null,
            tint = Color.Black
        )
    }
}


@Composable
@Preview
fun CartItemPreview() {
    CartItem(CartFakeData.cartItem)
}