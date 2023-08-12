package com.yangyinxu.finitude.presentation.screens.player

import android.net.Uri
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FileOpen
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.ui.PlayerView
import com.yangyinxu.finitude.MainViewModel
import com.yangyinxu.finitude.ui.theme.LARGE_PADDING
import com.yangyinxu.finitude.util.Constants.PLAYER_INPUT_STRING

@Composable
fun PlayerScreen(
    viewModel: MainViewModel,
    videoItems: List<VideoItem>,
    selectVideoLauncher: ManagedActivityResultLauncher<String, Uri?>,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        DefaultPlayerScreen(
            viewModel = viewModel,
            videoItems = videoItems,
            selectVideoLauncher = selectVideoLauncher
        )
        /*
        TODO: Add Portrait Player Screen with more details

        val configuration = LocalConfiguration.current
        when (configuration.orientation) {
            Configuration.ORIENTATION_LANDSCAPE -> {

            }
            else -> {

            }
        }
         */
    }
}

@Composable
fun DefaultPlayerScreen(
    viewModel: MainViewModel,
    videoItems: List<VideoItem>,
    selectVideoLauncher: ManagedActivityResultLauncher<String, Uri?>,
) {
    viewModel.player.playWhenReady = true

    AndroidView(
        factory = { context ->
            PlayerView(context).also { playerView ->
                playerView.player = viewModel.player
            }
        },
        update = {
            /*
            when (lifecycle) {
                Lifecycle.Event.ON_PAUSE -> {
                    it.onPause()
                    it.player?.pause()
                }
                Lifecycle.Event.ON_RESUME -> {
                    it.onResume()
                }
                else -> Unit
            }
             */
        },
        modifier = Modifier
            .fillMaxSize()
        // TODO: should be adjusted for different screen size
        // .aspectRatio()
    )
    PlayerTools(
        viewModel,
        videoItems,
        selectVideoLauncher
    )
}

@Composable
fun PlayerTools(
    viewModel: MainViewModel,
    videoItems: List<VideoItem>,
    selectVideoLauncher: ManagedActivityResultLauncher<String, Uri?>
) {
    Column {
        IconButton(
            onClick = {
                // Note: this filters the file by file type
                // (e.g. "video/*", "video/mp4")
                selectVideoLauncher.launch(PLAYER_INPUT_STRING)
            }
        ) {
            Icon(
                imageVector = Icons.Default.FileOpen,
                contentDescription = "Select Video"
            )
        }
        Spacer(
            modifier = Modifier
                .height(16.dp)
        )
        LazyColumn(
            modifier = Modifier
        ) {
            items(videoItems.size) { index ->
                val videoItem = videoItems[index]
                Text(
                    text = videoItem.name,
                    modifier = Modifier
                        .clickable {
                            viewModel.playVideo(videoItem.contentUri)
                        }
                        .padding(LARGE_PADDING)
                )
            }
        }
    }
}