package com.yangyinxu.finitude

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.navigation.compose.rememberNavController
import com.yangyinxu.finitude.navigation.SetupNavigation
import com.yangyinxu.finitude.presentation.bottomNavBar.BottomNavItem
import com.yangyinxu.finitude.presentation.bottomNavBar.BottomNavigationBar
import com.yangyinxu.finitude.ui.theme.FinitudeTheme
import com.yangyinxu.finitude.util.Constants

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FinitudeTheme {
                val navController = rememberNavController()
                Scaffold(
                    bottomBar = {
                        BottomNavigationBar(
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
                ) {
                    SetupNavigation(
                        navController = navController
                    )
                }
            }
        }
    }
}