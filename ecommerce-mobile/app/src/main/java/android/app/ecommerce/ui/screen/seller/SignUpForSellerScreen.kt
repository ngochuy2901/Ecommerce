package android.app.ecommerce.ui.screen.seller

import androidx.compose.runtime.Composable

import android.app.ecommerce.R
import android.app.ecommerce.data.authentication.Auth
import android.app.ecommerce.ui.component.SellerInput
import android.app.ecommerce.viewmodel.auth.SignUpForSellerViewModel
import android.app.ecommerce.viewmodel.auth.SignUpForSellerViewModelFactory
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController

@Composable
fun SignUpForSellerScreen(navController: NavController) {

    val context = LocalContext.current
    val auth = Auth(context)

    var shopName by remember { mutableStateOf("") }
    var shopDescription by remember { mutableStateOf("") }
    var shopAddress by remember { mutableStateOf("") }
    var identityNumber by remember { mutableStateOf("") }

    val viewModel: SignUpForSellerViewModel = viewModel(
        factory = SignUpForSellerViewModelFactory(auth)
    )

    val isLoading by viewModel.isLoading
    val isSignUpSuccess by viewModel.isSignUpSuccess
    val signUpError by viewModel.signUpError

    // SUCCESS
    LaunchedEffect(isSignUpSuccess) {
        if (isSignUpSuccess) {
            Toast.makeText(context, "Đăng ký người bán thành công", Toast.LENGTH_SHORT).show()
            navController.navigate("sign_up_seller") {
                popUpTo("signUpForSeller") { inclusive = true }
            }
        }
    }

    // ERROR
    LaunchedEffect(signUpError) {
        signUpError?.let {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        // HEADER
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            IconButton(
                onClick = { navController.popBackStack() },
                modifier = Modifier
                    .size(45.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFF5F6FA))
                    .align(Alignment.Start)
            ) {
                Icon(
                    painter = painterResource(R.drawable.icon_arrow_back),
                    contentDescription = "Back"
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Đăng ký người bán",
                fontSize = 26.sp,
                fontWeight = FontWeight.SemiBold
            )
        }

        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.align(Alignment.Center)
        ) {

            SellerInput(
                label = "Tên cửa hàng",
                value = shopName,
                onValueChange = { shopName = it }
            )

            SellerInput(
                label = "Mô tả cửa hàng",
                value = shopDescription,
                onValueChange = { shopDescription = it },
                singleLine = false
            )

            SellerInput(
                label = "Địa chỉ cửa hàng",
                value = shopAddress,
                onValueChange = { shopAddress = it }
            )

            SellerInput(
                label = "Số CCCD / CMND",
                value = identityNumber,
                onValueChange = { identityNumber = it }
            )
        }
        // BUTTON
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .align(Alignment.BottomCenter)
                .clip(RoundedCornerShape(12.dp))
                .background(
                    if (isLoading) Color.Gray else Color(0xFF9775FA)
                )
                .clickable(enabled = !isLoading) {
                    viewModel.signUpForSeller(
                        shopName,
                        shopDescription,
                        shopAddress,
                        identityNumber
                    )
                },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = if (isLoading) "Đang xử lý..." else "Đăng ký người bán",
                fontSize = 17.sp,
                color = Color.White,
                fontWeight = FontWeight.Medium
            )
        }
    }


}

