package com.yangyinxu.finitude.presentation.screens.player

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.yangyinxu.finitude.ui.theme.appBarBackgroundColor
import com.yangyinxu.finitude.ui.theme.appBarContentColor

@Composable
fun MiniPlayerControl(videoItems: List<VideoItem>) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(75.dp)
            .background(MaterialTheme.colors.appBarBackgroundColor)
    ) {
        var filename = ""
        if (videoItems.isNotEmpty()) {
            filename = videoItems[0].name
        }
        Text(
            text = "Playing: $filename",
            color = MaterialTheme.colors.appBarContentColor
        )
    }
}