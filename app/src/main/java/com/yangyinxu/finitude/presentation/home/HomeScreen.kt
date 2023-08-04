package com.yangyinxu.finitude.presentation.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.yangyinxu.finitude.R
import com.yangyinxu.finitude.presentation.posts.PostItem

@Composable
fun HomeScreen(bottomNavBarPadding: PaddingValues) {
    val state = rememberLazyGridState()

    Box(
        modifier = Modifier
            .padding(bottomNavBarPadding)
    ) {
        HomeScreenContent(state = state)
    }
}

@Composable
fun HomeScreenContent(state: LazyGridState) {
    LazyVerticalGrid(
        state = state,
        columns = GridCells.Adaptive(400.dp),
        content = {
            items(20) { index ->
                PostItem(
                    id = index,
                    title = stringResource(id = R.string.test_title),
                    description = stringResource(id = R.string.test_description),
                    imageUrl = stringResource(id = R.string.test_image_url)
                )
            }
        }
    )
}