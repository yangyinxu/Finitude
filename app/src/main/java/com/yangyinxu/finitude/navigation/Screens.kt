package com.yangyinxu.finitude.navigation

import androidx.navigation.NavHostController
import com.yangyinxu.finitude.util.Constants.ROUTE_HOME
import com.yangyinxu.finitude.util.Constants.ROUTE_LOGIN
import com.yangyinxu.finitude.util.Constants.ROUTE_PLAYER

class Screens(navController: NavHostController) {

    val home: () -> Unit = {
        navController.navigate(ROUTE_HOME) {
            popUpTo(navController.graph.id) {
                inclusive = false
            }
        }
    }

    val postDetails: (Int) -> Unit = { postId ->
        navController.navigate("postDetails/$postId")
    }

    val player: () -> Unit = {
        navController.navigate(ROUTE_PLAYER)
    }

    val login: () -> Unit = {
        navController.navigate(ROUTE_LOGIN)
    }
}