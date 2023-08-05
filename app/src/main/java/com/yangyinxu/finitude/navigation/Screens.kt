package com.yangyinxu.finitude.navigation

import androidx.navigation.NavHostController
import com.yangyinxu.finitude.util.Constants.ROUTE_HOME

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
}