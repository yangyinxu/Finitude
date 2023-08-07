package com.yangyinxu.finitude.domain.use_case

/**
 * Middle layer between viewmodel and data layer,
 * so each class only has one responsibility.
 * example: it can be used to access repository
 */
class ValidatePassword {

    fun execute(password: String): ValidationResult {
        // insufficient length
        if (password.length < 8) {
            return ValidationResult(
                successful = false,
                errorMessage = "The password needs to be consist of at least 8 characters"
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