package android.app.ecommerce.ui.component

import android.app.ecommerce.R
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SearchBar(modifier: Modifier) {
    var searchContent by remember { mutableStateOf("") }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        TextField(
            value = searchContent,
            placeholder = { Text("Search", fontSize = 15.sp, color = Color(0xFF8F959E)) },
            onValueChange = { searchContent = it },
//            colors = TextFieldDefaults.textFieldColors(
//                focusedIndicatorColor = Color.Transparent,
//                unfocusedIndicatorColor = Color.Transparent
//            ),
            modifier = Modifier
                .weight(1f)
                .background(Color(0xFFF5F6FA), shape = RoundedCornerShape(10.dp))
        )
        IconButton(
            onClick = {},
            modifier = Modifier
                .size(50.dp)
                .background(Color(0xFF9775FA), shape = RoundedCornerShape(10.dp))
        ) {
            Icon(
                painterResource(R.drawable.icon_micro),
                null,
                modifier = Modifier.background(Color(0xFF9775FA), shape = RoundedCornerShape(10.dp))
            )
        }
    }
}

@Composable
@Preview
fun SearchBarPreview() {
    SearchBar(modifier = Modifier)
}