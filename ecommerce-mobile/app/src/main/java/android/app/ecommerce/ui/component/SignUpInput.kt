package android.app.ecommerce.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SignUpInput(modifier: Modifier = Modifier) {

    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var isOn by remember { mutableStateOf(false) }

    Column(
        modifier = modifier,
        verticalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(16.dp)
    ) {

        Text(
            text = "Username",
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
                }
            ,
            textStyle = TextStyle(
                fontSize = 15.sp
            ),

        )

        Text(
            text = "Password",
            fontSize = 13.sp,
            color = Color(0xFF8F959E)
        )
        BasicTextField(
            value = password,
            onValueChange = { password = it },
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
            visualTransformation = PasswordVisualTransformation(),
        )

        Text(
            text = "Email Address",
            fontSize = 13.sp,
            color = Color(0xFF8F959E)
        )
        BasicTextField(
            value = email,
            onValueChange = { email = it },
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
            singleLine = true,

        )
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Remember me", fontSize = 13.sp)
            Switch(
                checked = isOn,
                onCheckedChange = { isOn = it }
            )
        }
    }
}


@Composable
@Preview
fun SignUpInputPreview() {
    SignUpInput()
}