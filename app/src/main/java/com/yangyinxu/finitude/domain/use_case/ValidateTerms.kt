package com.yangyinxu.finitude.domain.use_case

/**
 * Middle layer between viewmodel and data layer,
 * so each class only has one responsibility.
 * example: it can be used to access repository
 */
class ValidateTerms {

    fun execute(accepted: Boolean): ValidationResult {
        if (!accepted) {
            return ValidationResult(
                successful = false,
                errorMessage = "Please accept the terms."
            )
        }

        return ValidationResult(
            successful = true
        )
    }
}