package android.app.ecommerce.ui.component

import android.app.ecommerce.R
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun UserBottomNavigation(navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
    ) {
        UserBottomNavigationItem(
            icon = R.drawable.icon_home,
            label = "Home",
            modifier = Modifier.weight(1f).clickable {
                navController.navigate("home")
            }
        )

        UserBottomNavigationItem(
            icon = R.drawable.icon_product,
            label = "Product",
            modifier = Modifier.weight(1f)
        )

        UserBottomNavigationItem(
            icon = R.drawable.icon_order,
            label = "Order",
            modifier = Modifier.weight(1f)
        )

        UserBottomNavigationItem(
            icon = R.drawable.icon_setting,
            label = "Setting",
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
fun UserBottomNavigationItem(
    icon: Int,
    label: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            painter = painterResource(icon),
            contentDescription = label,
            modifier = Modifier.size(24.dp)
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = label,
            fontSize = 12.sp
        )
    }
}


@Composable
@Preview
fun UserBottomNavigationPreview() {
    UserBottomNavigation(rememberNavController())
}