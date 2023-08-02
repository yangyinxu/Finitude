package com.yangyinxu.finitude.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.yangyinxu.finitude.presentation.chat.ChatScreen
import com.yangyinxu.finitude.presentation.home.HomeScreen
import com.yangyinxu.finitude.presentation.settings.SettingsScreen
import com.yangyinxu.finitude.util.Constants

@Composable
fun SetupNavigation(
    navController: NavHostController,
    // sharedViewModel: SharedViewModel
) {
    NavHost(
        navController = navController,
        startDestination = Constants.ROUTE_HOME
    ) {
        composable(Constants.ROUTE_HOME) {
            HomeScreen()
        }
        composable(Constants.ROUTE_CHAT) {
            ChatScreen()
        }
        composable(Constants.ROUTE_SETTINGS) {
            SettingsScreen()
        }
    }
}