package com.yangyinxu.finitude.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen() {
    val state = rememberLazyGridState()
    LazyVerticalGrid(
        state = state,
        columns = GridCells.Adaptive(200.dp),
        content = {
            items(100) { index ->
                Box(
                   modifier = Modifier
                       .padding(8.dp)
                       .aspectRatio(1f)
                       .clip(RoundedCornerShape(5.dp))
                       .background(Color.Green),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "Item $index")
                }
            }
        }
    )
}