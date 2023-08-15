package com.yangyinxu.finitude.presentation.screens.settings.login

data class LoginFormState(
    val email: String = "",
    val emailError: String? = null,
    val password: String = "",
    val passwordError: String? = null
)