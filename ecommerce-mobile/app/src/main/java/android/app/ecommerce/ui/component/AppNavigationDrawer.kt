package android.app.ecommerce.ui.component

import android.app.ecommerce.R
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavigationDrawer(
    drawerState: DrawerState,
    content: @Composable () -> Unit
) {
    val scope = rememberCoroutineScope()

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

                    NavigationDrawerItem(
                        label = { Text("Home") },
                        selected = false,
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
                }
            }
        }
    ) {
        content()
    }
}

@Composable
fun DrawerMenuIcon(
    drawerState: DrawerState
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
            onClick = {}, modifier = Modifier
                .size(45.dp)
                .clip(CircleShape)
                .background(Color(0xFFF5F6FA))
        ) {
            Icon(painterResource(R.drawable.icon_lock), null)
        }
    }
}
