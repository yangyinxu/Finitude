package com.yangyinxu.finitude.presentation.screens.settings.registration

sealed class RegistrationFormEvent {
    data class UsernameChanged(val username: String): RegistrationFormEvent()
    data class EmailChanged(val email: String): RegistrationFormEvent()
    data class PasswordChanged(val password: String): RegistrationFormEvent()
    data class RepeatedPasswordChanged(val repeatedPassword: String): RegistrationFormEvent()
    data class TermsAcceptanceChanged(val isAccepted: Boolean): RegistrationFormEvent()

    object Submit: RegistrationFormEvent()
}
