package com.yangyinxu.finitude

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.media3.common.Player
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.yangyinxu.finitude.navigation.SetupNavigation
import com.yangyinxu.finitude.presentation.bottomNavBar.MainBottomNavBar
import com.yangyinxu.finitude.ui.theme.FinitudeTheme
import com.yangyinxu.finitude.util.Constants.ROUTE_PLAYER
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(
            window,
            false
        )

        setContent {
            FinitudeTheme {
                val navController = rememberNavController()
                val viewModel = hiltViewModel<MainViewModel>()
                val videoItems by viewModel.videoItems.collectAsState()
                val selectVideoLauncher = rememberLauncherForActivityResult(
                    contract = ActivityResultContracts.GetContent(),
                    onResult = { uri ->
                        // if the uri exist, add to the viewmodel
                        for (videoItem in videoItems) {
                            if (videoItem.contentUri == uri) {
                                // return if the given contentUri already exists
                                return@rememberLauncherForActivityResult
                            }
                        }
                        uri?.let(viewModel::addVideoUri)
                    }
                )
                // TODO: Look for a better way to pause the player when
                //  the activity is paused
                // this variable will help sync the lifecycle with the player
                /*
                var lifecycle by remember {
                    mutableStateOf(Lifecycle.Event.ON_CREATE)
                }
                val lifecycleOwner = LocalLifecycleOwner.current
                DisposableEffect(lifecycleOwner) {
                    val observer = LifecycleEventObserver { _, event ->
                        lifecycle = event
                    }

                    lifecycleOwner.lifecycle.addObserver(observer)

                    // called when the current screen leaves composition
                    // (e.g. destroyed)
                    onDispose {
                        lifecycleOwner.lifecycle.removeObserver(observer)
                    }
                }
                 */

                // Mini Player UI control
                var isPlayReady by remember {
                    mutableStateOf(viewModel.player.playbackState == Player.STATE_READY)
                }
                var isContentPlaying by remember {
                    mutableStateOf(viewModel.player.isPlaying)
                }
                val playerListener = object: Player.Listener {
                    override fun onPlaybackStateChanged(playbackState: Int) {
                        super.onPlaybackStateChanged(playbackState)
                        // Player.STATE_BUFFERING
                        isPlayReady = playbackState == Player.STATE_READY
                    }

                    override fun onIsPlayingChanged(isPlaying: Boolean) {
                        super.onIsPlayingChanged(isPlaying)
                        isContentPlaying = isPlaying
                    }
                }
                viewModel.player.addListener(playerListener)

                // Video Player UI control
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route
                val systemUiController: SystemUiController = rememberSystemUiController()
                Scaffold(
                    bottomBar = {
                        // hide system UI
                        systemUiController.isStatusBarVisible = (currentRoute != ROUTE_PLAYER) // Status bar
                        systemUiController.isNavigationBarVisible = (currentRoute != ROUTE_PLAYER) // Navigation bar
                        systemUiController.isSystemBarsVisible = (currentRoute != ROUTE_PLAYER) // Status & Navigation bars
                        if (currentRoute == ROUTE_PLAYER) {
                            systemUiController.systemBarsBehavior =
                                WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
                        } else {
                            systemUiController.systemBarsBehavior =
                                WindowInsetsControllerCompat.BEHAVIOR_DEFAULT
                        }
                        // lock to landscape mode for player
                        /*
                        if (currentRoute == ROUTE_PLAYER)  {
                            this.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE
                        } else {
                            this.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
                        }
                         */
                        // hide the bottom bar when the route is player
                        if (currentRoute != ROUTE_PLAYER) {
                            MainBottomNavBar(
                                navController = navController,
                                viewModel = viewModel,
                                videoItems = videoItems,
                                isPlayReady = isPlayReady,
                                isPlaying = isContentPlaying
                            )
                        }
                    }
                ) { bottomNavBarPadding ->
                    Box(
                        modifier = Modifier
                            .padding(bottomNavBarPadding)
                            .statusBarsPadding().systemBarsPadding()
                    ) {
                        SetupNavigation(
                            navController = navController,
                            viewModel = viewModel,
                            videoItems = videoItems,
                            selectVideoLauncher = selectVideoLauncher
                        )
                    }
                }
            }
        }
    }
}