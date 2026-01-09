package android.app.ecommerce.ui.screen

import android.app.ecommerce.ui.component.LayoutWithHeaderBackIconButton
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AddressScreen() {
    LayoutWithHeaderBackIconButton("Address") {
        AddressForm()
    }
}

@Composable
@Preview
fun AddressScreenPreview() {
    AddressScreen()
}

@Composable
fun AddressForm() {
    var isPrimary by remember { mutableStateOf(true) }
    var addressName by remember { mutableStateOf("") }
    var country by remember { mutableStateOf("") }
    var city by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {

            // Name
            LabelText("Name")
            RoundedTextField(value = addressName, onValueChange = { addressName = it })

            // Country - City
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    LabelText("Country")
                    RoundedTextField(value = country, onValueChange = { country = it })
                }
                Column(modifier = Modifier.weight(1f)) {
                    LabelText("City")
                    RoundedTextField(value = city, onValueChange = { city = it })
                }
            }

            // Phone
            LabelText("Phone Number")
            RoundedTextField(value = phoneNumber, onValueChange = { phoneNumber = it })

            // Address
            LabelText("Address")
            RoundedTextField(
                value = address,
                onValueChange = { address = it })

            // Switch
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Save as primary address",
                    fontSize = 14.sp,
                    modifier = Modifier.weight(1f)
                )
                Switch(
                    checked = isPrimary,
                    onCheckedChange = { isPrimary = it }
                )
            }
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
                text = "Save address",
                fontSize = 17.sp,
                color = Color.White
            )
        }

    }
}

@Composable
fun LabelText(text: String) {
    Text(
        text = text,
        fontSize = 14.sp,
        fontWeight = FontWeight.Medium,
        modifier = Modifier.padding(bottom = 4.dp)
    )
}

@Composable
fun RoundedTextField(
    value: String,
    onValueChange: (String) -> Unit,
    singleLine: Boolean = true,
    height: Dp = 50.dp
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier
            .fillMaxWidth()
            .height(height),
        singleLine = singleLine,
        shape = RoundedCornerShape(12.dp),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color(0xFFF5F6FA),
            unfocusedContainerColor = Color(0xFFF5F6FA),
            disabledContainerColor = Color(0xFFF5F6FA),

            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        )
    )
}
