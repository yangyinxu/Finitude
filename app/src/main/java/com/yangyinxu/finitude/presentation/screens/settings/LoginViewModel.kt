package com.yangyinxu.finitude.presentation.screens.settings

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yangyinxu.finitude.data.remote.archResponses.login.LoginRequestBody
import com.yangyinxu.finitude.domain.repository.ArchtreeRepository
import com.yangyinxu.finitude.domain.use_case.ValidateEmail
import com.yangyinxu.finitude.domain.use_case.ValidatePassword
import com.yangyinxu.finitude.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val archRepo: ArchtreeRepository,
    private val validateEmail: ValidateEmail,
    private val validatePassword: ValidatePassword
): ViewModel() {

    var state by mutableStateOf(LoginFormState())
    private val validationEventChannel = Channel<ValidationEvent>()
    val validationEvents = validationEventChannel.receiveAsFlow()

    private var emailResult = validateEmail.execute(state.email)
    private var passwordResult = validatePassword.execute(state.password)

    sealed class ValidationEvent {
        object LocalValidationSuccess: ValidationEvent()
        object RemoteValidationSuccess: ValidationEvent()
    }

    fun onEvent(event: LoginFormEvent) {
        when (event) {
            is LoginFormEvent.EmailChanged -> {
                // make an independent copy of the state (event.email is immutable)
                emailResult = validateEmail.execute(event.email)
                state = state.copy(email = event.email, emailError = emailResult.errorMessage)
            }
            is LoginFormEvent.PasswordChanged -> {
                passwordResult = validatePassword.execute(event.password)
                state = state.copy(password = event.password, passwordError = passwordResult.errorMessage)
            }
            is LoginFormEvent.Submit -> {
                submitData()
            }
        }
    }

    private fun submitData() {
        val hasError = listOf(
            emailResult,
            passwordResult
        ).any { it.errorMessage != null }

        if (hasError) {
            state = state.copy(
                emailError = emailResult.errorMessage,
                passwordError = passwordResult.errorMessage
            )
            return
        }

        // send login request to the api
        viewModelScope.launch {
            val loginRequestBody = LoginRequestBody(
                state.email, state.password
            )
            when (val loginResult = archRepo.postLogin(loginRequestBody)) {
                is Resource.Success -> {
                    validationEventChannel.send(ValidationEvent.RemoteValidationSuccess)
                }
                else -> {
                    println("Failed to login")
                    println(loginResult.message)
                }
            }
        }
    }
}