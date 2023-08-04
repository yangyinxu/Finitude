package com.yangyinxu.finitude

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material.Scaffold
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.navigation.compose.rememberNavController
import com.yangyinxu.finitude.presentation.player.PlayerScreen
import com.yangyinxu.finitude.ui.theme.FinitudeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FinitudeTheme {
                val navController = rememberNavController()
                val viewModel = hiltViewModel<MainViewModel>()
                val videoItems by viewModel.videoItems.collectAsState()
                val selectVideoLauncher = rememberLauncherForActivityResult(
                    contract = ActivityResultContracts.GetContent(),
                    onResult = { uri ->
                        // if the uri exist, add to the viewmodel
                        uri?.let(viewModel::addVideoUri)
                    }
                )
                // this variable will help sync the lifecycle with the player
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

                PlayerScreen(
                    viewModel,
                    videoItems,
                    selectVideoLauncher,
                    lifecycle
                )

                /*
                Scaffold(
                    bottomBar = {
                        MainBottomNavBar(navController = navController)
                    }
                ) {
                    Navigation(
                        navController = navController
                    )
                }
                 */
            }
        }
    }
}