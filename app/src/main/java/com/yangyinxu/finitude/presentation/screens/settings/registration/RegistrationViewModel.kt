package com.yangyinxu.finitude.presentation.screens.settings.registration

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yangyinxu.finitude.data.remote.archResponses.signUp.SignUpRequestBody
import com.yangyinxu.finitude.data.remote.archResponses.signUp.SignUpResponse
import com.yangyinxu.finitude.domain.repository.ArchtreeRepository
import com.yangyinxu.finitude.domain.use_case.ValidateEmail
import com.yangyinxu.finitude.domain.use_case.ValidatePassword
import com.yangyinxu.finitude.domain.use_case.ValidateRepeatedPassword
import com.yangyinxu.finitude.domain.use_case.ValidateTerms
import com.yangyinxu.finitude.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val archRepo: ArchtreeRepository,
    private val validateEmail: ValidateEmail,
    private val validatePassword: ValidatePassword,
    private val validateRepeatedPassword: ValidateRepeatedPassword,
    private val validateTerms: ValidateTerms
) : ViewModel() {
    var state by mutableStateOf(RegistrationFormState())

    // We can send events into the channel, so the UI can collect
    // the changes from the flow
    private val validationEventChannel = Channel<ValidationEvent>()
    val validationEvents = validationEventChannel.receiveAsFlow()

    private var emailResult = validateEmail.execute(state.email)
    private var passwordResult = validatePassword.execute(state.password)
    private var repeatedPasswordResult = validateRepeatedPassword.execute(
        state.password, state.repeatedPassword)
    private var termsResult = validateTerms.execute(state.acceptedTerms)

    fun onEvent(event: RegistrationFormEvent) {
        when (event) {
            is RegistrationFormEvent.EmailChanged -> {
                // make an independent copy of the state (event.email is immutable)
                emailResult = validateEmail.execute(event.email)
                state = state.copy(email = event.email, emailError = emailResult.errorMessage)
            }
            is RegistrationFormEvent.PasswordChanged -> {
                passwordResult = validatePassword.execute(event.password)
                state = state.copy(password = event.password, passwordError = passwordResult.errorMessage)
            }
            is RegistrationFormEvent.RepeatedPasswordChanged -> {
                repeatedPasswordResult = validateRepeatedPassword.execute(
                    state.password, event.repeatedPassword)
                state = state.copy(repeatedPassword = event.repeatedPassword, repeatedPasswordError = repeatedPasswordResult.errorMessage)
            }
            is RegistrationFormEvent.TermsAcceptanceChanged -> {
                termsResult = validateTerms.execute(event.isAccepted)
                state = state.copy(acceptedTerms = event.isAccepted, termsError = termsResult.errorMessage)
            }
            is RegistrationFormEvent.Submit -> {
                submitData()
            }
        }
    }

    private fun submitData() {
        val hasError = listOf(
            emailResult,
            passwordResult,
            repeatedPasswordResult,
            termsResult
        ).any { it.errorMessage != null }

        if (hasError) {
            state = state.copy(
                emailError = emailResult.errorMessage,
                passwordError = passwordResult.errorMessage,
                repeatedPasswordError = repeatedPasswordResult.errorMessage,
                termsError = termsResult.errorMessage
            )
            return
        }

        // launch coroutine
        viewModelScope.launch {
            validationEventChannel.send(ValidationEvent.LocalValidationSuccess)

            // send signup request
            // TODO: Add nickname field rather than using email as name
            val signUpRequestBody = SignUpRequestBody(
                state.email, state.password, state.email)
            when (val signUpResult = archRepo.putSignUp(signUpRequestBody)) {
                is Resource.Success -> {
                    val signupResponse: SignUpResponse? = signUpResult.data
                    println("Account created, userId: " + signupResponse?.userId)
                    validationEventChannel.send(ValidationEvent.RemoteValidationSuccess)
                }
                else -> {
                    println("Failed to create a new account")
                }
            }
        }
    }

    sealed class ValidationEvent {
        object LocalValidationSuccess: ValidationEvent()
        object RemoteValidationSuccess: ValidationEvent()
        object RemoteValidationFailed: ValidationEvent()
    }
}