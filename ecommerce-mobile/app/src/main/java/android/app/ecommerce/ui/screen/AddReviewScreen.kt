package android.app.ecommerce.ui.screen

import android.app.ecommerce.R
import android.app.ecommerce.data.fakedata.ReviewFakeData
import android.app.ecommerce.ui.component.RatingStars
import android.app.ecommerce.ui.component.ReviewList
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
fun AddReviewScreen() {
    var name by remember { mutableStateOf("") }
    var experience by remember { mutableStateOf("") }
    var rating by remember { mutableStateOf(0f) }

    Box(modifier = Modifier.fillMaxSize()) {
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
                        onClick = {},
                        modifier = Modifier
                            .size(45.dp)
                            .clip(CircleShape)
                            .align(Alignment.CenterStart)
                            .background(Color(0xFFF5F6FA))
                    ) {
                        Icon(painterResource(R.drawable.icon_arrow_back), null)
                    }

                    Text(
                        text = "Review",
                        fontSize = 17.sp,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }

            //fuck
            item {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {

                    // NAME
                    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                        Text(
                            text = "Name",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium
                        )

                        OutlinedTextField(
                            value = name,
                            onValueChange = { name = it },
                            placeholder = {
                                Text("Type your name")
                            },
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(12.dp),
                            colors = OutlinedTextFieldDefaults.colors(
                                unfocusedContainerColor = Color(0xFFF5F6FA),
                                focusedContainerColor = Color(0xFFF5F6FA),
                                unfocusedBorderColor = Color.Transparent,
                                focusedBorderColor = Color.Transparent
                            )
                        )
                    }

                    // EXPERIENCE
                    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                        Text(
                            text = "How was your experience ?",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium
                        )

                        OutlinedTextField(
                            value = experience,
                            onValueChange = { experience = it },
                            placeholder = {
                                Text("Describe your experience?")
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(120.dp),
                            shape = RoundedCornerShape(12.dp),
                            maxLines = 5,
                            colors = OutlinedTextFieldDefaults.colors(
                                unfocusedContainerColor = Color(0xFFF5F6FA),
                                focusedContainerColor = Color(0xFFF5F6FA),
                                unfocusedBorderColor = Color.Transparent,
                                focusedBorderColor = Color.Transparent
                            )
                        )
                    }

                    // STAR
                    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                        Text(
                            text = "Star",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium
                        )

                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "1",
                                fontSize = 12.sp,
                                color = Color.Gray
                            )

                            Slider(
                                value = rating,
                                onValueChange = { rating = it },
                                valueRange = 1f..5f,
                                steps = 3, // 👈 5 mức: 1–2–3–4–5
                                modifier = Modifier.weight(1f),
                                colors = SliderDefaults.colors(
                                    thumbColor = Color(0xFF8A2BE2),
                                    activeTrackColor = Color(0xFF8A2BE2),
                                    inactiveTrackColor = Color(0xFFE0E0E0)
                                )
                            )

                            Text(
                                text = "5",
                                fontSize = 12.sp,
                                color = Color.Gray
                            )
                        }
                    }

                }
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
                text = "Submit review",
                fontSize = 17.sp,
                color = Color.White
            )
        }
    }
}

@Composable
@Preview
fun AddReviewScreenPreview() {
    AddReviewScreen()
}