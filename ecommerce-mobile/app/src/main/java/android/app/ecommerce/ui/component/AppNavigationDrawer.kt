package android.app.ecommerce.ui.component

import android.app.ecommerce.R
import android.app.ecommerce.data.authentication.Auth
import android.app.ecommerce.data.dto.UserDto
import android.app.ecommerce.data.fakedata.UserFakeData
import android.app.ecommerce.data.model.Role
import android.app.ecommerce.data.model.User
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavigationDrawer(
    drawerState: DrawerState,
    navController: NavController,
    content: @Composable () -> Unit,
) {
    var showDialog by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val auth = Auth(context)
    val userInfo = auth.getUserInfo()
    ActionDialog(
        showDialog,
        "You're not seller, sign in as seller?",
        "Do you want to sign in as seller?",
        "Yes",
        "No",
        onCancel = {showDialog = false},
        onConfirm = {navController.navigate("sign_up_seller")}) { }
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
                    if (userInfo != null) {
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
                            onClick = { navController.navigate("login") }
                        )
                    }

                    if (auth.isLoggedIn()) {
                        NavigationDrawerItem(
                            label = { Text("Switch account") },
                            selected = false,
                            icon = {
                                Icon(
                                    painterResource(R.drawable.icon_switch_account),
                                    contentDescription = null
                                )
                            },
                            onClick = {
                                navController.navigate("seller_home_screen")
                            }
                        )
                    }

                    NavigationDrawerItem(
                        label = { Text("Settings") },
                        selected = false,
                        icon = {
                            Icon(
                                painterResource(R.drawable.icon_setting),
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
                                painterResource(R.drawable.icon_logout),
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
fun AppNavigationDrawerPreview() {
    AppNavigationDrawer(rememberDrawerState(DrawerValue.Open), rememberNavController(), {})
}

@Composable
fun DrawerMenuIcon(
    drawerState: DrawerState,
    navController: NavController
) {
    val scope = rememberCoroutineScope()

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(
            onClick = {
                scope.launch { drawerState.open() }
            }, modifier = Modifier
                .size(45.dp)
                .clip(CircleShape)
                .background(Color(0xFFF5F6FA))
        ) {
            Icon(painterResource(R.drawable.icon_view_list), null)
        }
        IconButton(
            onClick = {
                navController.navigate("cart")
            }, modifier = Modifier
                .size(45.dp)
                .clip(CircleShape)
                .background(Color(0xFFF5F6FA))
        ) {
            Icon(painterResource(R.drawable.icon_cart), null)
        }
    }
}

@Composable
@Preview
fun DrawerMenuIconPreview() {
    DrawerMenuIcon(rememberDrawerState(DrawerValue.Open), rememberNavController())
}

@Composable
fun UserInfo(userDto: UserDto, navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween // chia đều khoảng cách
    ) {
        Image(
            painter = painterResource(R.drawable.ic_launcher_background),
            contentDescription = "User Avatar",
            modifier = Modifier
                .size(45.dp)
                .clip(CircleShape)
        )

        Spacer(modifier = Modifier.width(12.dp))

        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = userDto.username,
                fontSize = 17.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Black
            )
            Text(
                text = "Verified Profile",
                fontSize = 13.sp,
                color = Color(0xFF8F959E)
            )
        }

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .width(70.dp)
                .height(35.dp)
                .background(Color(0xFFF5F5F5), shape = RoundedCornerShape(8.dp))
        ) {
            Text(
                text = "3 Orders",
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                color = Color(0xFF8F959E)
            )
        }
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
@Preview
fun UserInfoPreview() {
    UserInfo(UserFakeData.userDtoExample, rememberNavController())
}
