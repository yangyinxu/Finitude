package com.yangyinxu.finitude.presentation.screens.settings.registration

data class RegistrationFormState(
    val username: String = "",
    val usernameError: String? = null,
    val email: String = "",
    val emailError: String? = null,
    val password: String = "",
    val passwordError: String? = null,
    val repeatedPassword: String = "",
    val repeatedPasswordError: String? = null,
    val acceptedTerms: Boolean = false,
    val termsError: String? = null
)