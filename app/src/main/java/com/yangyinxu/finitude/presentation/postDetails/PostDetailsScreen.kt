package com.yangyinxu.finitude.presentation.postDetails

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.yangyinxu.finitude.R
import com.yangyinxu.finitude.presentation.posts.PostTitle
import com.yangyinxu.finitude.ui.theme.postBgColor

@Composable
fun PostDetails(
    navigateToHomeScreen: () -> Unit,
    id: Int = -1
) {
    Row(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .clip(RoundedCornerShape(20.dp))
            .border(
                border = BorderStroke(2.dp, Color.DarkGray),
                shape = RoundedCornerShape(20.dp)
            )
            .background(MaterialTheme.colors.postBgColor)
            .padding(10.dp)
    ) {
        PostTitle(title = stringResource(id = R.string.test_title))
    }
}