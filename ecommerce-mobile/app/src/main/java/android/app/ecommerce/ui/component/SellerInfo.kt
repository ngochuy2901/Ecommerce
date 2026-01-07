package android.app.ecommerce.ui.component

import android.app.ecommerce.R
import android.app.ecommerce.data.fakedata.SellerProfileFakeData
import android.app.ecommerce.data.model.SellerProfile
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
@Composable
fun SellerInfo(sellerProfile: SellerProfile) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(160.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        // Header
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Reviews",
                fontSize = 17.sp,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = "View all",
                fontSize = 14.sp,
                color = Color(0xFF8F959E)
            )
        }

        // Seller row
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Image(
                painter = painterResource(R.drawable.ic_launcher_background),
                contentDescription = null,
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
            )

            Spacer(modifier = Modifier.width(10.dp))

            Column {
                Text(
                    text = sellerProfile.shopName,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Medium
                )
                Text(
                    text = sellerProfile.createdAt.toString(),
                    fontSize = 13.sp,
                    color = Color(0xFF8F959E)
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            Column(
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = "Rating",
                    fontSize = 13.sp,
                    color = Color(0xFF8F959E)
                )
                RatingStars(
                    rating = 4.5f,
                    starSize = 14.dp
                )
            }
        }

        // Description
        Text(
            text = sellerProfile.shopDescription ?: "",
            fontSize = 14.sp,
            color = Color(0xFF8F959E),
            maxLines = 2
        )
    }
}


@Composable
@Preview
fun SellerInfoPreview() {
    SellerInfo(SellerProfileFakeData.sellerProfile)
}

@Composable
fun RatingStars(
    rating: Float,
    maxStars: Int = 5,
    starSize: Dp = 16.dp,
    space: Dp = 2.dp
) {
    Row(horizontalArrangement = Arrangement.spacedBy(space)) {
        for (i in 1..maxStars) {
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = null,
                tint = if (i <= rating) Color(0xFFFFC107) else Color.LightGray,
                modifier = Modifier.size(starSize)
            )
        }
    }
}
