package com.yangyinxu.finitude.presentation.screens.home

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayCircle
import androidx.compose.runtime.Composable
import com.yangyinxu.finitude.ui.theme.*

@Composable
fun DefaultHomeAppBar(
    onPlayerClicked: () -> Unit
) {
    TopAppBar(
        title = {
            Text(
                text = "Home",
            )
        },
        actions = {
            PlayerAction(onPlayerClicked)
        },
        backgroundColor = MaterialTheme.colors.topAppBarBackgroundColor
    )
}

@Composable
fun PlayerAction(
    onPlayerClicked: () -> Unit
) {
    IconButton(
        onClick = { onPlayerClicked() }
    ) {
        Icon(
            imageVector = Icons.Filled.PlayCircle,
            contentDescription = "Player Icon",
            tint = MaterialTheme.colors.topAppBarContentColor
        )
    }
}