package com.project.meomi.domain.user.presentation.data.request

import javax.validation.constraints.NotBlank
import javax.validation.constraints.Pattern


data class SignInRequest(
    @field:NotBlank
    @field:Pattern(regexp = "^[a-zA-Z0-9]+@gsm.hs.kr\$")
    val email: String,
    @field:NotBlank
    val password: String
)