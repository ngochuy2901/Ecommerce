package android.app.ecommerce.ui.component

import android.app.ecommerce.R
import android.app.ecommerce.data.fakedata.ReviewFakeData
import android.app.ecommerce.data.model.Review
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ReviewList(reviewList: List<Review>) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        reviewList.forEach { review ->
            ReviewItem(review)
        }
    }
//    LazyColumn {
//        items(reviewList) { review ->
//            ReviewItem(review)
//        }
//    }
}

@Composable
@Preview
fun ReviewListPreview() {
    ReviewList(ReviewFakeData.reviewList)
//    Button(
//        onClick = {},
//        modifier = Modifier
//            .height(35.dp),
//        shape = RoundedCornerShape(8.dp),
//        contentPadding = PaddingValues(horizontal = 12.dp),
//        colors = ButtonDefaults.buttonColors(
//            containerColor = Color(0xFFFF7043),
//            contentColor = Color.White
//        )
//    ) {
//        Icon(
//            painter = painterResource(R.drawable.icon_edit),
//            contentDescription = null,
//            modifier = Modifier.size(15.dp)
//        )
//
//        Spacer(modifier = Modifier.width(6.dp))
//
//        Text(
//            text = "Add Review",
//            fontSize = 13.sp,
//            fontWeight = FontWeight.Medium
//        )
//    }
}

@Composable
fun ReviewItem(review: Review) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            // Avatar
            Image(
                painter = painterResource(R.drawable.ic_launcher_background),
                contentDescription = null,
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
            )

            Spacer(modifier = Modifier.width(10.dp))

            // Name + date
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "Ngoc Huy",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Medium
                )

                Spacer(modifier = Modifier.height(2.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(R.drawable.icon_lock),
                        contentDescription = null,
                        modifier = Modifier.size(13.dp),
                        tint = Color.Gray
                    )

                    Spacer(modifier = Modifier.width(4.dp))

                    Text(
                        text = "29/01/01",
                        fontSize = 11.sp,
                        color = Color.Gray
                    )
                }
            }

            // Rating
            Column(
                horizontalAlignment = Alignment.End
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "4.8",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Medium
                    )

                    Spacer(modifier = Modifier.width(4.dp))

                    Text(
                        text = "rating",
                        fontSize = 12.sp,
                        color = Color.Gray
                    )
                }

                Spacer(modifier = Modifier.height(2.dp))

                RatingStars(4.toFloat())
            }
        }
        Text(review.comment, fontSize = 15.sp, color = Color(0xFF8F959E))
    }
}

@Composable
@Preview
fun ReviewItemPreview() {
    ReviewItem(ReviewFakeData.review)
}