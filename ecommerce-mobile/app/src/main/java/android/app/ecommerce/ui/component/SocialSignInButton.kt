package android.app.ecommerce.ui.component

import android.app.ecommerce.R
import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SocialSignInButton(modifier: Modifier = Modifier) {
    Column(verticalArrangement = Arrangement.spacedBy(10.dp), modifier = modifier) {
        SocialSignInItem(
            R.drawable.ic_launcher_background,
            "Facebook",
            backgroundColor = Color(0xFF4267B2)
        )
        SocialSignInItem(
            R.drawable.ic_launcher_background,
            "Twitter",
            backgroundColor = Color(0xFF1DA1F2)
        )
        SocialSignInItem(
            R.drawable.ic_launcher_background,
            "Google",
            backgroundColor = Color(0xFFEA4335)
        )
    }
}

@Composable
@Preview
fun SocialSignInButtonPreview() {
    Box {
        SocialSignInButton(modifier = Modifier.align(Alignment.Center))
    }
}

@Composable
fun SocialSignInItem(
    @DrawableRes icon: Int,
    text: String,
    backgroundColor: Color
) {
    Button(
        onClick = {},
        modifier = Modifier
            .width(335.dp)
            .height(50.dp),
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor
        )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                painter = painterResource(icon),
                contentDescription = null,
                tint = Color.White
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = text,
                fontSize = 17.sp,
                color = Color.White
            )
        }
    }
}


@Composable
@Preview
fun SocialSignInItemPreview() {
    SocialSignInItem(
        R.drawable.ic_launcher_background,
        "Facebook",
        backgroundColor = Color(0xFF4267B2)
    )
}