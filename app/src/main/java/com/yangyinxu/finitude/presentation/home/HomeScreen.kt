package com.yangyinxu.finitude.presentation.home

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.yangyinxu.finitude.R
import com.yangyinxu.finitude.presentation.posts.PostItem

@Composable
fun HomeScreen(
    navigateToPostDetails: (Int) -> Unit
) {
    val state = rememberLazyGridState()
    LazyVerticalGrid(
        state = state,
        columns = GridCells.Adaptive(400.dp),
        content = {
            items(20) { index ->
                PostItem(
                    navigateToPostDetails = navigateToPostDetails,
                    id = index,
                    title = stringResource(id = R.string.test_title),
                    description = stringResource(id = R.string.test_description),
                    imageUrl = stringResource(id = R.string.test_image_url)
                )
            }
        }
    )
}