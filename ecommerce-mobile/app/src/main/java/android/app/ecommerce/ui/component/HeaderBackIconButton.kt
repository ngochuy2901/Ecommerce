package android.app.ecommerce.ui.component

import android.app.ecommerce.R
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LayoutWithHeaderBackIconButton(
    title: String,
    onBackClick: () -> Unit, content: @Composable () -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        item {
            Box(
                modifier = Modifier
                    .height(45.dp)
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                IconButton(
                    onClick = {
                        onBackClick()
                    },
                    modifier = Modifier
                        .size(45.dp)
                        .clip(CircleShape)
                        .align(Alignment.CenterStart)
                        .background(Color(0xFFF5F6FA))
                ) {
                    Icon(painterResource(R.drawable.icon_arrow_back), null)
                }

                Text(
                    text = title,
                    fontSize = 17.sp,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
        item { content() }
    }
}

@Composable
@Preview
fun LayoutWithHeaderBackIconButtonPreview() {
    LayoutWithHeaderBackIconButton("Title", {}, { Text("Header") })
}