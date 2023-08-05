package com.yangyinxu.finitude.presentation.postDetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import coil.compose.AsyncImage
import com.yangyinxu.finitude.R
import com.yangyinxu.finitude.ui.theme.LARGE_PADDING

@Composable
fun PostDetails(
    navigateToHomeScreen: () -> Unit,
    id: Int = -1
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth(),
            model = stringResource(id = R.string.test_image_url),
            contentDescription = "Post Image"
        )
        PostDetailsContent(
            title = stringResource(id = R.string.test_title),
            description = stringResource(id = R.string.test_description)
        )
    }
}

@Composable
fun PostDetailsContent(
    title: String,
    description: String
) {
    Column(
        modifier = Modifier
            .padding(LARGE_PADDING)
    ) {
        Row {
            Text(
                text = title,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
        Text(
            modifier = Modifier
                .padding(top = LARGE_PADDING),
            text = description
        )
    }
}