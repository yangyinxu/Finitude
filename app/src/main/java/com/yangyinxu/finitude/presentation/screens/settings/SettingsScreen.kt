package com.yangyinxu.finitude.presentation.screens.settings

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.yangyinxu.finitude.R
import com.yangyinxu.finitude.ui.theme.LARGE_PADDING
import com.yangyinxu.finitude.ui.theme.MEDIUM_PADDING

@Composable
fun SettingsScreen(
    navigateToLogIn: () -> Unit,
    navigateToAccountDetails: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Settings Screen")
    }
    Row {
        Account(
            navigateToLogin = navigateToLogIn,
            navigateToAccountDetails = navigateToAccountDetails
        )
    }
}

@Composable
fun Account(
    navigateToLogin: () -> Unit,
    navigateToAccountDetails: () -> Unit
) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(LARGE_PADDING)
            .clip(MaterialTheme.shapes.large)
            .background(MaterialTheme.colors.primaryVariant)
            .border(
                border = BorderStroke(
                    2.dp,
                    MaterialTheme.colors.secondary
                ),
                shape = MaterialTheme.shapes.large
            )
            .clickable {
                // TODO: Navigate to Login page or Account Details page
                navigateToLogin()
            }
    ) {
        AccountIcon(
            stringResource(id = R.string.test_profile_picture_url)
        )
        AccountInfo(
            username = stringResource(id = R.string.test_username),
            email = stringResource(id = R.string.test_email)
        )
    }
}

@Composable
fun AccountIcon(
    imageUrl: String
) {
    AsyncImage(
        modifier = Modifier
            .size(125.dp)
            .padding(MEDIUM_PADDING)
            .clip(MaterialTheme.shapes.medium),
        model = imageUrl,
        contentDescription = "Post Image",
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AccountInfo(
    username: String,
    email: String,
) {
    Column(
        modifier = Modifier
            .padding(MEDIUM_PADDING)
    ) {
        Text(
            modifier = Modifier
                .basicMarquee(),
            text = username,
            style = MaterialTheme.typography.h5,
            maxLines = 1
        )
        Text(
            modifier = Modifier
                .basicMarquee(),
            text = email,
            maxLines = 1
        )
    }
}