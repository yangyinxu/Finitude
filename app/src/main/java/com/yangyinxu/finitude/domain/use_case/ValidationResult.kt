package com.yangyinxu.finitude.domain.use_case

data class ValidationResult(
    val successful: Boolean,
    val errorMessage: String? = null
)
