package com.yangyinxu.finitude.domain.use_case

import com.yangyinxu.finitude.util.Constants

/**
 * Middle layer between viewmodel and data layer,
 * so each class only has one responsibility.
 * example: it can be used to access repository
 */
class ValidateRepeatedPassword {

    fun execute(password: String, repeatedPassword: String): ValidationResult {
        // blank email address
        if (password != repeatedPassword) {
            return ValidationResult(
                successful = false,
                errorMessage = "The passwords don't match"
            )
        }

        // insufficient length
        if (password.length < Constants.MIN_PASSWORD_LENGTH) {
            return ValidationResult(
                successful = false,
                errorMessage = "The password needs to be consist of at least ${Constants.MIN_PASSWORD_LENGTH} characters"
            )
        }

        // check if password contains at least one digit and one letter
        val containsLettersAndDigits = password.any { it.isDigit() }
                && password.any { it.isLetter() }
        if (!containsLettersAndDigits) {
            return ValidationResult(
                successful = false,
                errorMessage = "The password needs to contain at least one letter and digit"
            )
        }

        return ValidationResult(
            successful = true
        )
    }
}