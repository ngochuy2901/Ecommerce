package android.app.ecommerce.ui.screen.user


import android.annotation.SuppressLint
import android.app.ecommerce.R
import android.app.ecommerce.data.authentication.Auth
import android.app.ecommerce.data.fakedata.CartFakeData
import android.app.ecommerce.data.model.CartItem
import android.app.ecommerce.ui.component.LayoutWithHeaderBackIconButton
import android.app.ecommerce.viewmodel.user.CartViewModel
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@SuppressLint("SuspiciousIndentation")
@Composable
fun CartScreen(
    navController: NavController,
    viewModel: CartViewModel = viewModel()
) {
    val subtotal by viewModel.subtotal
    val listCartItem by viewModel.listCartItem
    val context = LocalContext.current
    val auth = Auth(LocalContext.current)
    if(!auth.isLoggedIn()) {
        Toast.makeText(context, "Please login", Toast.LENGTH_SHORT).show()
        navController.navigate("home")
    }
    val isCheckoutEnabled = listCartItem.isNotEmpty()
    val isLoading by viewModel.isLoading
        Box(modifier = Modifier.fillMaxSize()) {
            LayoutWithHeaderBackIconButton("Cart", {navController.popBackStack()}) {
                if (isLoading) {
                    CircularProgressIndicator()
                } else {
                    Column {
                        ListCartItem(listCartItem)
                        Box() {
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(Color(0xFFF7F7F7))
                                    .padding(16.dp),
                                verticalArrangement = Arrangement.spacedBy(16.dp)
                            ) {
                                DeliveryAddressSection()
                                PaymentMethodSection()
                                OrderInfoSection(
                                    subtotal = subtotal,
                                    shipping = 10.0
                                )
                            }
                        }
                    }
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .align(Alignment.BottomCenter)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color(0xFF9775FA))
                    .clickable(enabled = isCheckoutEnabled) {
                        navController.navigate("check_out")
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
fun DeliveryAddressSection(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Card(
        shape = RoundedCornerShape(12.dp),
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick() },
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier.padding(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.LocationOn,
                contentDescription = null,
                tint = Color(0xFF9775FA),
                modifier = Modifier.size(22.dp)
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "Delivery Address",
                    fontSize = 13.sp,
                    color = Color(0xFF8F959E)
                )
                Text(
                    text = "Chhatak, Sunamgonj 12/8AB",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = "Sylhet",
                    fontSize = 12.sp,
                    color = Color(0xFF8F959E)
                )
            }

            Icon(
                imageVector = Icons.Default.CheckCircle,
                contentDescription = null,
                tint = Color(0xFF2ECC71)
            )
        }
    }
}
@Composable
fun PaymentMethodSection(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Card(
        shape = RoundedCornerShape(12.dp),
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick() },
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier.padding(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(R.drawable.ic_launcher_background), // icon visa thật
                contentDescription = null,
                modifier = Modifier.size(36.dp)
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "Payment Method",
                    fontSize = 13.sp,
                    color = Color(0xFF8F959E)
                )
                Text(
                    text = "Visa Classic",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = "**** 7690",
                    fontSize = 12.sp,
                    color = Color(0xFF8F959E)
                )
            }

            Icon(
                imageVector = Icons.Default.CheckCircle,
                contentDescription = null,
                tint = Color(0xFF2ECC71)
            )
        }
    }
}
@Composable
fun OrderInfoSection(
    subtotal: Double,
    shipping: Double
) {
    val total = subtotal + shipping

    Card(
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text(
                text = "Order Info",
                fontWeight = FontWeight.SemiBold,
                fontSize = 15.sp
            )

            PriceRow("Subtotal", subtotal)
            PriceRow("Shipping cost", shipping)

            Divider(color = Color(0xFFF0F0F0))

            PriceRow(
                title = "Total",
                value = total,
                isBold = true,
                highlight = true
            )
        }
    }
}

@Composable
fun PriceRow(
    title: String,
    value: Double,
    isBold: Boolean = false,
    highlight: Boolean = false
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = title,
            color = Color(0xFF8F959E)
        )
        Text(
            text = "${value.toInt()} đ",
            fontWeight = if (isBold) FontWeight.Bold else FontWeight.Normal,
            color = if (highlight) Color(0xFF9775FA) else Color.Black
        )
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