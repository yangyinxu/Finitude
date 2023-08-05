package com.yangyinxu.finitude

import android.net.Uri
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import com.yangyinxu.finitude.presentation.screens.player.MetadataReader
import com.yangyinxu.finitude.presentation.screens.player.VideoItem
import com.yangyinxu.finitude.util.Constants.PLAYER_SUBSCRIPTION_TIMEOUT
import com.yangyinxu.finitude.util.Constants.PLAYER_VIDEO_URIS_KEY
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    // contains the bundle or map of arguments/variables that
    //  survives process death
    private val savedStateHandle: SavedStateHandle,
    val player: Player,
    private val metadataReader: MetadataReader
): ViewModel() {

    private val videoUris =
        savedStateHandle.getStateFlow(
            PLAYER_VIDEO_URIS_KEY,
            emptyList<Uri>()
        )

    // whenever videoUris changes, we will get these uris as a state
    val videoItems = videoUris.map { uris ->
        uris.map { uri ->
            VideoItem(
                contentUri = uri,
                mediaItem = MediaItem.fromUri(uri),
                name = metadataReader.getMetadataFromUri(uri)?.fileName ?: "No name"
            )
        }
    }.stateIn(
        // create a stateflow
        viewModelScope,
        // making sure that the map block will only execute when
        //  there are subscribers
        SharingStarted.WhileSubscribed(PLAYER_SUBSCRIPTION_TIMEOUT),
        emptyList()
    )

    init {
        player.prepare()
    }

    fun addVideoUri(uri: Uri) {
        savedStateHandle[PLAYER_VIDEO_URIS_KEY] = videoUris.value + uri
        player.addMediaItem(MediaItem.fromUri(uri))
    }

    fun playVideo(uri: Uri) {
        player.setMediaItem(
            // find the media item with the same contentUri,
            //  else return
            videoItems.value.find { it.contentUri == uri }?.mediaItem ?: return
        )
    }

    override fun onCleared() {
        super.onCleared()
        player.release()
    }
}