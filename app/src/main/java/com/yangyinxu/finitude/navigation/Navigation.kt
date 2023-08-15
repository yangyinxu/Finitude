package com.yangyinxu.finitude.navigation

import android.annotation.SuppressLint
import android.net.Uri
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.yangyinxu.finitude.MainViewModel
import com.yangyinxu.finitude.presentation.screens.chat.ChatScreen
import com.yangyinxu.finitude.presentation.screens.home.HomeScreen
import com.yangyinxu.finitude.presentation.screens.player.PlayerScreen
import com.yangyinxu.finitude.presentation.screens.player.VideoItem
import com.yangyinxu.finitude.presentation.screenItems.postDetails.PostDetails
import com.yangyinxu.finitude.presentation.screens.settings.login.LoginScreen
import com.yangyinxu.finitude.presentation.screens.settings.SettingsScreen
import com.yangyinxu.finitude.util.Constants
import com.yangyinxu.finitude.util.Constants.POST_DETAILS_ARGUMENT_KEY

@SuppressLint("RememberReturnType")
@Composable
fun SetupNavigation(
    navController: NavHostController,
    viewModel: MainViewModel,
    videoItems: List<VideoItem>,
    selectVideoLauncher: ManagedActivityResultLauncher<String, Uri?>,
    // sharedViewModel: SharedViewModel
) {

    val screen = remember(navController) {
        Screens(navController = navController)
    }

    NavHost(
        navController = navController,
        startDestination = Constants.ROUTE_HOME
    ) {
        // Home
        composable(
            route = Constants.ROUTE_HOME
        ) {
            HomeScreen(
                navigateToPostDetails = screen.postDetails,
                navigateToPlayer = screen.player
            )
        }

        // Post Details
        composable(
            route = Constants.ROUTE_POST_DETAILS,
            arguments = listOf(
                navArgument(POST_DETAILS_ARGUMENT_KEY) {
                    type = NavType.IntType
                }
            )
        ) { navBackStackEntry ->
            val postId = navBackStackEntry.arguments!!.getInt(POST_DETAILS_ARGUMENT_KEY)

            PostDetails(
                navigateToHomeScreen = screen.home,
                id = postId,
            )
        }

        // Chat
        composable(
            route = Constants.ROUTE_CHAT
        ) {
            ChatScreen()
        }

        // Player
        composable(
            route = Constants.ROUTE_PLAYER
        ) {
            PlayerScreen(
                viewModel = viewModel,
                videoItems = videoItems,
                selectVideoLauncher = selectVideoLauncher
            )
        }

        // Settings
        composable(
            route = Constants.ROUTE_SETTINGS
        ) {
            SettingsScreen(
                navigateToLogIn = screen.login,
                navigateToAccountDetails = {
                    println("navigate to account details")
                }
            )
        }

        // Login
        composable(
            route = Constants.ROUTE_LOGIN
        ) {
            LoginScreen(
                navController = navController
            )
        }
    }
}