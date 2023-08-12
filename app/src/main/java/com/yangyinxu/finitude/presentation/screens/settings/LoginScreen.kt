package com.yangyinxu.finitude.presentation.screens.settings

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.yangyinxu.finitude.R
import com.yangyinxu.finitude.ui.theme.MEDIUM_PADDING
import com.yangyinxu.finitude.util.Constants.ROUTE_SIGN_UP

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginViewModel = hiltViewModel()
) {
    Surface(
        color = MaterialTheme.colors.background,
        modifier = Modifier.fillMaxSize()
    ) {
        val state = viewModel.loginFormState
        val context = LocalContext.current
        val keyboardController = LocalSoftwareKeyboardController.current

        //  LaunchedEffect is executed once when entered inside the composition.
        //  And it is canceled when leaving the composition.
        LaunchedEffect(key1 = context) {
            // collect all events sent through the channel
            viewModel.validationEvents.collect() { event ->
                when (event) {
                    is LoginViewModel.ValidationEvent.LocalValidationSuccess -> {
                        Toast.makeText(
                            context,
                            "Login Local Validation Successful",
                            Toast.LENGTH_LONG
                        ).show()
                    }

                    is LoginViewModel.ValidationEvent.RemoteValidationSuccess -> {
                        Toast.makeText(
                            context,
                            "Login Remote Validation Successful",
                            Toast.LENGTH_LONG
                        ).show()
                    }

                    else -> {
                        Toast.makeText(
                            context,
                            "Login Remote Validation Failed, please verify your email and password",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Logo(
                modifier = Modifier
                    .size(125.dp)
                    .padding(MEDIUM_PADDING)
                    .clip(MaterialTheme.shapes.medium)
                    .align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(16.dp))
            EmailAddressTextField(
                state = state,
                viewModel = viewModel
            )
            Spacer(modifier = Modifier.height(16.dp))
            PasswordTextField(
                state = state,
                keyboardController = keyboardController,
                viewModel = viewModel
            )
            Spacer(modifier = Modifier.height(16.dp))
            LoginSubmitButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally),
                viewModel = viewModel
            )
            Spacer(modifier = Modifier.height(16.dp))
            RegistrationButton(
                navController = navController,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally)
            )
        }
    }
}

@Composable
fun Logo(
    modifier: Modifier
) {
    AsyncImage(
        modifier = modifier,
        model = stringResource(id = R.string.test_profile_picture_url),
        contentDescription = "Logo",
    )
}

@Composable
fun EmailAddressTextField(
    state: LoginFormState,
    viewModel: LoginViewModel
) {
    TextField(
        value = state.email,
        onValueChange = {
            viewModel.onEvent(
                LoginFormEvent.EmailChanged(it)
            )
        },
        isError = state.emailError != null,
        modifier = Modifier.fillMaxWidth(),
        placeholder = {
            Text(text = "Email")
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Next
        ),
        maxLines = 1,
        singleLine = true,
    )

    if (state.emailError != null) {
        Text(
            text = state.emailError,
            color = MaterialTheme.colors.error
        )
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun PasswordTextField(
    state: LoginFormState,
    keyboardController: SoftwareKeyboardController?,
    viewModel: LoginViewModel
) {
    TextField(
        value = state.password,
        onValueChange = {
            viewModel.onEvent(LoginFormEvent.PasswordChanged(it))
        },
        isError = state.passwordError != null,
        modifier = Modifier.fillMaxWidth(),
        placeholder = {
            Text(text = "Password")
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = { keyboardController?.hide() }
        ),
        visualTransformation = PasswordVisualTransformation(),
        maxLines = 1,
        singleLine = true,
    )
    if (state.passwordError != null) {
        Text(
            text = state.passwordError,
            color = MaterialTheme.colors.error,
        )
    }
}

@Composable
fun LoginSubmitButton(
    modifier: Modifier,
    viewModel: LoginViewModel
) {
    val context = LocalContext.current
    Button(
        modifier = modifier,
        onClick = {
            Toast.makeText(
                context,
                "Logging in...",
                Toast.LENGTH_LONG
            ).show()
            viewModel.onEvent(LoginFormEvent.Submit)
        }
    ) {
        Text(text = "Log In")
    }
}

@Composable
fun RegistrationButton(
    navController: NavController,
    modifier: Modifier
) {
    Button(
        modifier = modifier,
        onClick = {
            println("Sign Up button clicked")
            // TODO: navigate to Signup page
            // navController.navigate(ROUTE_SIGN_UP)
        }
    ) {
        Text(text = "Sign Up")
    }
}