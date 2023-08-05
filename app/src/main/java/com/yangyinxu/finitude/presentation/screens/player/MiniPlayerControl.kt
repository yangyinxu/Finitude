package com.yangyinxu.finitude.presentation.screens.player

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yangyinxu.finitude.MainViewModel
import com.yangyinxu.finitude.ui.theme.LARGE_PADDING
import com.yangyinxu.finitude.ui.theme.appBarBackgroundColor
import com.yangyinxu.finitude.ui.theme.appBarContentColor

@Composable
fun MiniPlayerControl(
    viewModel: MainViewModel?,
    videoItems: List<VideoItem>,
    isPlaying: Boolean
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.appBarBackgroundColor)
    ) {
        var filename = "Unknown filename"
        if (videoItems.isNotEmpty()) {
            filename = videoItems[0].name
        }
        Text(
            modifier = Modifier
                .weight(8f),
            text = "Playing: $filename",
            color = MaterialTheme.colors.appBarContentColor
        )
        Box(modifier = Modifier
            .weight(1f),
            contentAlignment = Alignment.CenterEnd) {
            IconButton(
                onClick = {
                    if (isPlaying) {
                        viewModel?.player?.pause()
                    } else {
                        viewModel?.player?.play()
                    }
                }
            ) {
                if (isPlaying) {
                    PauseIcon()
                } else {
                    PlayIcon()
                }
            }
        }

    }
}

@Composable
@Preview
fun MiniPlayerControlPreView() {
    MiniPlayerControl(
        videoItems = ArrayList(),
        isPlaying = true,
        viewModel = null
    )
}

@Composable
@Preview
fun PlayIcon() {
    Icon(
        modifier = Modifier
            .size(50.dp)
            .padding(LARGE_PADDING),
        imageVector = Icons.Default.PlayArrow,
        contentDescription = "Play Icon"
    )
}

@Composable
@Preview
fun PauseIcon() {
    Icon(
        modifier = Modifier
            .size(50.dp)
            .padding(LARGE_PADDING),
        imageVector = Icons.Default.Pause,
        contentDescription = "Play Icon"
    )
}