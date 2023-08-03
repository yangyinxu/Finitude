package com.yangyinxu.finitude.presentation.home

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.yangyinxu.finitude.presentation.posts.PostItemPreview

@Composable
fun HomeScreen() {
    val state = rememberLazyGridState()
    LazyVerticalGrid(
        state = state,
        columns = GridCells.Adaptive(200.dp),
        content = {
            items(100) { index ->
                PostItemPreview(id = index)
            }
        }
    )
}