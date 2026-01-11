package android.app.ecommerce.ui.screen

import android.app.ecommerce.R
import android.app.ecommerce.ui.component.SocialSignInButton
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun EntryAuthScreen(navController: NavController) {
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
                text = "Let’s Get Started",
                fontSize = 28.sp,
                fontWeight = FontWeight.SemiBold
            )
        }

        // Social buttons - CENTER
        SocialSignInButton(
            modifier = Modifier.align(Alignment.Center)
        )

        // Already have account - dưới social buttons
        Text(
            text = "Already have an account? Sign in",
            fontSize = 15.sp,
            color = Color.Gray,
            modifier = Modifier
                .align(Alignment.Center)
                .padding(top = 220.dp)
                .clickable {
                    navController.navigate("login")
                }
        )

        // Create Account button - bottom
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
                text = "Create an Account",
                fontSize = 17.sp,
                color = Color.White
            )
        }
    }
}


@Composable
@Preview
fun EntryAuthScreenPreview() {
    EntryAuthScreen(rememberNavController())
}

