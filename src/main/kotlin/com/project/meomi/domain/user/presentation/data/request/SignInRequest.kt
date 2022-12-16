package com.project.meomi.domain.user.presentation.data.request

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern


data class SignInRequest(
    @field:NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9]+@gsm.hs.kr\$")
    val email: String,
    @field:NotBlank
    val password: String
)