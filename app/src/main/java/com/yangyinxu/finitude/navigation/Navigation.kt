package com.yangyinxu.finitude.navigation

import android.net.Uri
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.yangyinxu.finitude.MainViewModel
import com.yangyinxu.finitude.presentation.chat.ChatScreen
import com.yangyinxu.finitude.presentation.home.HomeScreen
import com.yangyinxu.finitude.presentation.player.PlayerScreen
import com.yangyinxu.finitude.presentation.player.VideoItem
import com.yangyinxu.finitude.presentation.settings.SettingsScreen
import com.yangyinxu.finitude.util.Constants

@Composable
fun SetupNavigation(
    bottomNavBarPadding: PaddingValues,
    navController: NavHostController,
    viewModel: MainViewModel,
    videoItems: List<VideoItem>,
    selectVideoLauncher: ManagedActivityResultLauncher<String, Uri?>,
    lifecycle: Lifecycle.Event
    // sharedViewModel: SharedViewModel
) {
    NavHost(
        navController = navController,
        startDestination = Constants.ROUTE_HOME
    ) {
        composable(Constants.ROUTE_HOME) {
            HomeScreen(
                bottomNavBarPadding
            )
        }
        composable(Constants.ROUTE_CHAT) {
            ChatScreen(
                bottomNavBarPadding
            )
        }
        composable(Constants.ROUTE_PLAYER) {
            PlayerScreen(
                viewModel = viewModel,
                videoItems = videoItems,
                selectVideoLauncher = selectVideoLauncher,
                lifecycle = lifecycle)
        }
        composable(Constants.ROUTE_SETTINGS) {
            SettingsScreen(
                bottomNavBarPadding
            )
        }
    }
}