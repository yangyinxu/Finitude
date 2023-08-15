package com.yangyinxu.finitude.presentation.screens.settings.registration

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.material.Checkbox
import androidx.compose.material.MaterialTheme
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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.yangyinxu.finitude.presentation.screens.settings.login.Logo
import com.yangyinxu.finitude.ui.theme.MEDIUM_PADDING
import com.yangyinxu.finitude.util.Constants.ROUTE_LOGIN

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun RegistrationScreen(
    navController: NavController,
    viewModel: RegistrationViewModel = hiltViewModel()
) {
    val state = viewModel.state
    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current

    //  LaunchedEffect is executed once when entered inside the composition.
    //  And it is canceled when leaving the composition.
    LaunchedEffect(key1 = context) {
        // collect all events sent through the channel
        viewModel.validationEvents.collect() { event ->
            when (event) {
                is RegistrationViewModel.ValidationEvent.LocalValidationSuccess -> {
                    Toast.makeText(
                        context,
                        "Registration Local Validation Successful",
                        Toast.LENGTH_LONG
                    ).show()
                }
                is RegistrationViewModel.ValidationEvent.RemoteValidationSuccess -> {
                    Toast.makeText(
                        context,
                        "Registration Remote Validation Successful",
                        Toast.LENGTH_LONG
                    ).show()
                    // TODO: Navigate back to settings page
                    // navController.navigate()
                }
                is RegistrationViewModel.ValidationEvent.RemoteValidationFailed -> {
                    Toast.makeText(
                        context,
                        "Registration Local Validation Failed",
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
        UsernameField(state = state, viewModel = viewModel)
        Spacer(modifier = Modifier.height(16.dp))
        EmailAddressField(state = state, viewModel = viewModel)
        Spacer(modifier = Modifier.height(16.dp))
        PasswordField(state = state, viewModel = viewModel)
        Spacer(modifier = Modifier.height(16.dp))
        RepeatPasswordField(state = state, viewModel = viewModel, keyboardController = keyboardController)
        Spacer(modifier = Modifier.height(16.dp))
        TermsField(state = state, viewModel = viewModel)
        Spacer(modifier = Modifier.height(16.dp))
        RegistrationSubmitButton(modifier = Modifier.fillMaxWidth(), viewModel = viewModel)
        Spacer(modifier = Modifier.height(16.dp))
        LoginButton(navController = navController, modifier = Modifier.fillMaxWidth())
    }
}

@Composable
fun UsernameField(
    state: RegistrationFormState,
    viewModel: RegistrationViewModel
) {
    TextField(
        value = state.username,
        onValueChange = {
            viewModel.onEvent(RegistrationFormEvent.UsernameChanged(it))
        },
        isError = state.usernameError != null,
        modifier = Modifier.fillMaxWidth(),
        placeholder = {
            Text(text = "Username")
        },
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Next
        ),
        maxLines = 1,
        singleLine = true,
    )
    if (state.usernameError != null) {
        Text(
            text = state.usernameError,
            color = MaterialTheme.colors.error
        )
    }
}

@Composable
fun EmailAddressField(
    state: RegistrationFormState,
    viewModel: RegistrationViewModel
) {
    TextField(
        value = state.email,
        onValueChange = {
            viewModel.onEvent(RegistrationFormEvent.EmailChanged(it))
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

@Composable
fun PasswordField(
    state: RegistrationFormState,
    viewModel: RegistrationViewModel
) {
    TextField(
        value = state.password,
        onValueChange = {
            viewModel.onEvent(RegistrationFormEvent.PasswordChanged(it))
        },
        isError = state.passwordError != null,
        modifier = Modifier.fillMaxWidth(),
        placeholder = {
            Text(text = "Password")
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Next
        ),
        visualTransformation = PasswordVisualTransformation(),
        maxLines = 1,
        singleLine = true,
    )
    if (state.passwordError != null) {
        Text(
            text = state.passwordError,
            color = MaterialTheme.colors.error
        )
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun RepeatPasswordField(
    state: RegistrationFormState,
    viewModel: RegistrationViewModel,
    keyboardController: SoftwareKeyboardController?
) {
    TextField(
        value = state.repeatedPassword,
        onValueChange = {
            viewModel.onEvent(RegistrationFormEvent.RepeatedPasswordChanged(it))
        },
        isError = state.repeatedPasswordError != null,
        modifier = Modifier.fillMaxWidth(),
        placeholder = {
            Text(text = "Repeated Password")
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = {keyboardController?.hide()}
        ),
        visualTransformation = PasswordVisualTransformation(),
        maxLines = 1,
        singleLine = true,
    )

    if (state.repeatedPasswordError != null) {
        Text(
            text = state.repeatedPasswordError,
            color = MaterialTheme.colors.error
        )
    }
}

@Composable
fun TermsField(
    state: RegistrationFormState,
    viewModel: RegistrationViewModel
) {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        Checkbox(
            checked = state.acceptedTerms,
            onCheckedChange = {
                viewModel.onEvent(RegistrationFormEvent.TermsAcceptanceChanged(it))
            }
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = "Accept terms",
            modifier = Modifier.align(Alignment.CenterVertically))
    }
    if (state.termsError != null) {
        Text(
            text = state.termsError,
            color = MaterialTheme.colors.error
        )
    }
}

@Composable
fun RegistrationSubmitButton(
    modifier: Modifier,
    viewModel: RegistrationViewModel
) {
    Button(
        onClick = {
            println("Registration submit button clicked")
            viewModel.onEvent(RegistrationFormEvent.Submit)
        },
        modifier = modifier
    ) {
        Text(text = "Submit")
    }
}

@Composable
fun LoginButton(
    navController: NavController,
    modifier: Modifier
) {
    Button(
        modifier = modifier,
        onClick = {
            println("Login button clicked")
            navController.navigate(ROUTE_LOGIN) {
                popUpTo(ROUTE_LOGIN) {
                    inclusive = true
                }
            }
        }
    ) {
        Text(text = "Log In")
    }
}

