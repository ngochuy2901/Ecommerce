package android.app.ecommerce

import android.app.ecommerce.ui.component.AppNavigation
import android.app.ecommerce.ui.screen.AddressScreen
import android.app.ecommerce.ui.screen.HomeScreen
import android.app.ecommerce.ui.screen.SplashScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import android.app.ecommerce.ui.theme.EcommerceTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EcommerceTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)) {
                        AppNavigation()
                    }
                }
            }
        }
    }
}


//https://www.figma.com/design/aStonRDiqnMtoSHBpgN4PA/Laza---Ecommerce-Mobile-App-UI-Kit--Community-?node-id=1-1099&t=ODqhfQmAeUksgkUX-0