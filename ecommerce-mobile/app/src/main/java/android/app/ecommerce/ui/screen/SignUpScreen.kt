package android.app.ecommerce.ui.screen

import android.app.ecommerce.R
import android.app.ecommerce.data.authentication.Auth
import android.app.ecommerce.ui.component.SignUpInput
import android.app.ecommerce.viewmodel.SignUpViewModel
import android.app.ecommerce.viewmodel.SignUpViewModelFactory
import android.widget.Toast
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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

@Composable
fun SignUpScreen(navController: NavController) {
    val context = LocalContext.current
    val auth = Auth(context)
    val viewModel: SignUpViewModel = viewModel(
        factory = SignUpViewModelFactory(auth)
    )
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    val isSignUpSuccess by viewModel.isSignUpSuccess
    LaunchedEffect(isSignUpSuccess) {
        if(isSignUpSuccess) {
            Toast.makeText(context, "SignUp success!", Toast.LENGTH_SHORT).show()
            navController.navigate("login") {
                popUpTo("signup") { inclusive = true }
            }
        }
    }
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
                text = "SignUp",
                fontSize = 28.sp,
                fontWeight = FontWeight.SemiBold
            )
        }

        // Social buttons - CENTER
        SignUpInput(
            modifier = Modifier.align(Alignment.Center),
            username = username,
            password = password,
            email = email,
            onEmailChange = { email = it },
            onUsernameChange = {username = it},
            onPasswordChange = {password = it}
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
                    viewModel.signUp(username, password, email)
                },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Sign Up",
                fontSize = 17.sp,
                color = Color.White
            )
        }
    }
}

@Composable
@Preview
fun SignUpScreenPreview() {
    SignUpScreen(rememberNavController())
}