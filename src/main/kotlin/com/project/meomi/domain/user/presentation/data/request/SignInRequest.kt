package com.project.meomi.domain.user.presentation.data.request

import javax.validation.constraints.NotNull
import javax.validation.constraints.Pattern


data class SignInRequest(
    @field:NotNull
    @field:Pattern(regexp = "^[a-zA-Z0-9]+@gsm.hs.kr\$")
    val email: String,
    @field:NotNull
    val password: String
)