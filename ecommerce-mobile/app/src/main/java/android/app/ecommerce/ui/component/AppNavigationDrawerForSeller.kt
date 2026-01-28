package android.app.ecommerce.ui.component

import android.app.ecommerce.R
import android.app.ecommerce.data.authentication.Auth
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch

@Composable
fun AppNavigationDrawerForSeller(
    drawerState: DrawerState,
    navController: NavController,
    content: @Composable () -> Unit,
) {
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val auth = Auth(context)
    val userInfo = auth.getUserInfo()
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Column(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .verticalScroll(rememberScrollState())
                ) {
                    Spacer(Modifier.height(12.dp))
                    //
                    IconButton(
                        onClick = {
                            scope.launch {
                                drawerState.close()
                            }
                        }, modifier = Modifier
                            .size(45.dp)
                            .clip(CircleShape)
                            .background(Color(0xFFF5F6FA))
                    ) {
                        Icon(painterResource(R.drawable.icon_view_list), null)
                    }
                    //

                    HorizontalDivider()
                    if(userInfo!=null) {
                        NavigationDrawerItem(
                            label = { UserInfo(userInfo, navController) },
                            selected = false,
                            onClick = {
                                navController.navigate("profile")
                            }
                        )
                    } else {
                        NavigationDrawerItem(
                            label = { Text("Click to login") },
                            selected = false,
                            onClick = {navController.navigate("login")}
                        )
                    }

                    NavigationDrawerItem(
                        label = { Text("Dashboard") },
                        selected = false,
                        icon = {
                            Icon(
                                painterResource(R.drawable.icon_lock),
                                contentDescription = null
                            )
                        },
                        onClick = {}
                    )

                    NavigationDrawerItem(
                        label = { Text("Products") },
                        selected = false,
                        icon = {
                            Icon(
                                painterResource(R.drawable.icon_lock),
                                contentDescription = null
                            )
                        },
                        onClick = {
                            navController.navigate("product_management")
                        }
                    )

                    NavigationDrawerItem(
                        label = { Text("Order") },
                        selected = false,
                        icon = {
                            Icon(
                                painterResource(R.drawable.icon_lock),
                                contentDescription = null
                            )
                        },
                        onClick = {}
                    )

                    NavigationDrawerItem(
                        label = { Text("Shop profile") },
                        selected = false,
                        icon = {
                            Icon(
                                painterResource(R.drawable.icon_lock),
                                contentDescription = null
                            )
                        },
                        onClick = {}
                    )

                    NavigationDrawerItem(
                        label = { Text("Revenue and payment") },
                        selected = false,
                        icon = {
                            Icon(
                                painterResource(R.drawable.icon_lock),
                                contentDescription = null
                            )
                        },
                        onClick = {}
                    )

                    NavigationDrawerItem(
                        label = { Text("Rating and review") },
                        selected = false,
                        icon = {
                            Icon(
                                painterResource(R.drawable.icon_lock),
                                contentDescription = null
                            )
                        },
                        onClick = {}
                    )

                    NavigationDrawerItem(
                        label = { Text("Notification") },
                        selected = false,
                        icon = {
                            Icon(
                                painterResource(R.drawable.icon_lock),
                                contentDescription = null
                            )
                        },
                        onClick = {}
                    )

                    NavigationDrawerItem(
                        label = { Text("Settings") },
                        selected = false,
                        icon = {
                            Icon(
                                painterResource(R.drawable.icon_lock),
                                contentDescription = null
                            )
                        },
                        onClick = {}
                    )

                    NavigationDrawerItem(
                        label = { Text("Logout") },
                        selected = false,
                        icon = {
                            Icon(
                                painterResource(R.drawable.icon_lock),
                                contentDescription = null
                            )
                        },
                        onClick = {
                            auth.logout()
                            navController.navigate("entry_auth")
                        }
                    )
                }
            }
        }
    ) {
        content()
    }
}

@Composable
@Preview
fun AppNavigationDrawerForSellerPreview() {
    AppNavigationDrawerForSeller(rememberDrawerState(DrawerValue.Open), rememberNavController(), {})
}