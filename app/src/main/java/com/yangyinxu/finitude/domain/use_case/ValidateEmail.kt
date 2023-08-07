package com.yangyinxu.finitude.domain.use_case

import android.util.Patterns

/**
 * Middle layer between viewmodel and data layer,
 * so each class only has one responsibility.
 * example: it can be used to access repository
 */
class ValidateEmail {

    fun execute(email: String): ValidationResult {
        // blank email address
        if (email.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = "The email can't be blank"
            )
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return ValidationResult(
                successful = false,
                errorMessage = "Invalid email"
            )
        }

        return ValidationResult(
            successful = true
        )
    }
}