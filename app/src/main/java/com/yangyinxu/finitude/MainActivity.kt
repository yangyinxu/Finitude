package com.yangyinxu.finitude

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Scaffold
import androidx.navigation.compose.rememberNavController
import com.yangyinxu.finitude.navigation.Navigation
import com.yangyinxu.finitude.presentation.bottomNavBar.MainBottomNavBar
import com.yangyinxu.finitude.ui.theme.FinitudeTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FinitudeTheme {
                val navController = rememberNavController()
                Scaffold(
                    bottomBar = {
                        MainBottomNavBar(navController = navController)
                    }
                ) {
                    Navigation(
                        navController = navController
                    )
                }
            }
        }
    }
}