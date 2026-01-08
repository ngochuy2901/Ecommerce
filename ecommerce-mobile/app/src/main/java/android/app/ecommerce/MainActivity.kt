package android.app.ecommerce

import android.app.ecommerce.data.fakedata.FakeData
import android.app.ecommerce.data.fakedata.ReviewFakeData
import android.app.ecommerce.ui.component.AppNavigationDrawer
import android.app.ecommerce.ui.component.DrawerMenuIcon
import android.app.ecommerce.ui.component.NavigationDrawer
import android.app.ecommerce.ui.screen.AddReviewScreen
import android.app.ecommerce.ui.screen.HomeScreen
import android.app.ecommerce.ui.screen.ProductDetailScreen
import android.app.ecommerce.ui.screen.ReviewScreen
import android.app.ecommerce.ui.screen.SignUpScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import android.app.ecommerce.ui.theme.EcommerceTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EcommerceTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)) {
                        HomeScreen()
                    }
                }
            }
        }
    }
}


//https://www.figma.com/design/aStonRDiqnMtoSHBpgN4PA/Laza---Ecommerce-Mobile-App-UI-Kit--Community-?node-id=1-1099&t=ODqhfQmAeUksgkUX-0