package com.yangyinxu.finitude.domain.use_case

class ValidateUsername {

    fun execute(username: String): ValidationResult {
        // blank username
        if (username.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = "The username can't be blank"
            )
        }

        // insufficient length
        if (username.length < 5) {
            return ValidationResult(
                successful = false,
                errorMessage = "The username needs to be consist of at least 5 characters"
            )
        }

        return ValidationResult(
            successful = true
        )
    }
}