package com.project.meomi.domain.user.presentation.data.request

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern

data class SignUpRequest(
    @field:NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9]+@gsm.hs.kr\$")
    val email: String,
    @field:NotBlank
    val password: String,
    @field:NotBlank
    val name: String,
    @field:NotBlank
    val stuNum: String
)