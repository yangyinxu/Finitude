package com.yangyinxu.finitude.presentation.bottomNavBar

import androidx.compose.foundation.layout.Column
import androidx.compose.material.BadgedBox
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.PlayCircle
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.yangyinxu.finitude.util.Constants

@Composable
fun BottomNavBar(
    items: List<BottomNavItem>,
    navController: NavController,
    modifier: Modifier = Modifier,
    onItemClick: (BottomNavItem) -> Unit
) {
    val backStackEntry = navController.currentBackStackEntryAsState()
    BottomNavigation(
        modifier = modifier,
        backgroundColor = Color.DarkGray,
        elevation = 5.dp
    ) {
        items.forEach { item ->
            val selected = item.route == backStackEntry.value?.destination?.route
            BottomNavigationItem(
                selected = selected,
                onClick = {
                    onItemClick(item)
                },
                selectedContentColor = Color.Green,
                unselectedContentColor = Color.Gray,
                icon = {
                    Column(
                        horizontalAlignment = CenterHorizontally
                    ) {
                        if (item.badgeCount > 0) {
                            BadgedBox(
                                badge = {
                                    Text(text = item.badgeCount.toString())
                                }
                            ) {
                                Icon(
                                    imageVector = item.icon,
                                    contentDescription = item.name)
                            }
                        } else {
                            Icon(
                                imageVector = item.icon,
                                contentDescription = item.name)
                        }
                        if (selected) {
                            Text(
                                text = item.name,
                                textAlign = TextAlign.Center,
                                fontSize = 10.sp
                            )
                        }
                    }
                })
        }
    }
}

@Composable
fun MainBottomNavBar(
    navController: NavController
) {
    BottomNavBar(
        items = listOf(
            BottomNavItem(
                name = "Home",
                route = Constants.ROUTE_HOME,
                icon = Icons.Default.Home
            ),
            BottomNavItem(
                name = "Chat",
                route = Constants.ROUTE_CHAT,
                icon = Icons.Default.Chat
            ),
            BottomNavItem(
                name = "PLAYER",
                route = Constants.ROUTE_PLAYER,
                icon = Icons.Default.PlayCircle
            ),
            BottomNavItem(
                name = "Settings",
                route = Constants.ROUTE_SETTINGS,
                icon = Icons.Default.Settings
            )
        ),
        navController = navController,
        onItemClick = {
            navController.navigate(it.route)
        }
    )
}

@Composable
@Preview
fun MainBottomNavBarPreview() {
    val navController = rememberNavController()
    MainBottomNavBar(
        navController = navController
    )
}