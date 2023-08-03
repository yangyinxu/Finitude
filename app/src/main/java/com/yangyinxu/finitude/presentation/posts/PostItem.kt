package com.yangyinxu.finitude.presentation.posts

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.yangyinxu.finitude.R
import com.yangyinxu.finitude.ui.theme.postBgColor

@Composable
fun PostItem(
    id: Int = -1,
    title: String,
    description: String,
    imageUrl: String = "",
) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .aspectRatio(2f)
            .clip(RoundedCornerShape(20.dp))
            .background(MaterialTheme.colors.postBgColor)
            .padding(10.dp)
    ) {
        // Text(text = "Item $id")
        PostTitle(title = title)
        PostContent(
            description = description,
            imageUrl = imageUrl
        )
    }
}

@Composable
@Preview
fun PostItemPreview(
    id: Int = -1
) {
    PostItem(
        id = id,
        title = stringResource(id = R.string.test_title),
        description = stringResource(id = R.string.test_description),
        imageUrl = ""
        )
}

@Composable
fun PostTitle(
    title: String
) {
    Row {
        Text(
            modifier = Modifier
                .padding(bottom = 10.dp),
            text = title,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
fun PostContent(
    description: String,
    imageUrl: String = ""
) {
    Row {
        if (imageUrl.isNotEmpty()) {
            AsyncImage(
                model = imageUrl,
                contentDescription = "Post Image"
            )
        } else {
            Image(
                imageVector = Icons.Filled.Error,
                contentDescription = "Error Icon")
        }
        Text(
            modifier = Modifier
                .padding(start = 10.dp),
            text = description
        )
    }
}