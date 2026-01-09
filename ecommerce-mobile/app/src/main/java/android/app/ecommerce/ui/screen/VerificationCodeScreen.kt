package android.app.ecommerce.ui.screen

import android.app.ecommerce.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun VerificationCodeScreen(navController: NavController) {
    var username by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        // Header: Back + Title
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            IconButton(
                onClick = {},
                modifier = Modifier
                    .size(45.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFF5F6FA))
                    .align(Alignment.Start) // icon vẫn bên trái
            ) {
                Icon(
                    painter = painterResource(R.drawable.icon_arrow_back),
                    contentDescription = "Back"
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Verification Code",
                fontSize = 28.sp,
                fontWeight = FontWeight.SemiBold
            )
        }

        Column(
            modifier = Modifier.align(Alignment.Center),
            verticalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(16.dp),
        ) {
            Image(
                painterResource(R.drawable.image_cloud_lock),
                null,
                modifier = Modifier
                    .width(255.dp)
                    .height(166.dp)
                    .align(Alignment.CenterHorizontally)
            )
            Text(
                text = "Cần sửa đọan này",
                fontSize = 13.sp,
                color = Color(0xFF8F959E)
            )
            BasicTextField(
                value = username,
                onValueChange = { username = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(15.dp)
                    .drawBehind {
                        // vẽ gạch chân
                        val strokeWidth = 1.dp.toPx()
                        val y = size.height
                        drawLine(
                            color = Color.Gray,
                            start = androidx.compose.ui.geometry.Offset(0f, y),
                            end = androidx.compose.ui.geometry.Offset(size.width, y),
                            strokeWidth = strokeWidth
                        )
                    },
                textStyle = TextStyle(
                    fontSize = 15.sp
                ),
            )

        }


        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .align(Alignment.BottomCenter)
                .clip(RoundedCornerShape(12.dp))
                .background(Color(0xFF9775FA)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Confirm Code",
                fontSize = 17.sp,
                color = Color.White
            )
        }
    }
}

@Composable
@Preview
fun VerificationCodeScreenPreview() {
    VerificationCodeScreen(rememberNavController())
}