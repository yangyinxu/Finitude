package com.yangyinxu.finitude.presentation.screens.home

import android.annotation.SuppressLint
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.yangyinxu.finitude.R
import com.yangyinxu.finitude.presentation.screenItems.posts.PostItem

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    navigateToPostDetails: (Int) -> Unit,
    navigateToPlayer: () -> Unit
) {
    val state = rememberLazyGridState()
    Scaffold(
        topBar = {
            DefaultHomeAppBar(
                onPlayerClicked = navigateToPlayer
            )
        },
        content = {
            HomeScreenContent(
                navigateToPostDetails = navigateToPostDetails,
                state = state
            )
        }
    )
}

@Composable
fun HomeScreenContent(
    navigateToPostDetails: (Int) -> Unit,
    state: LazyGridState
) {
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
                    imageUrl = stringResource(id = R.string.test_album_image_url)
                )
            }
        }
    )
}