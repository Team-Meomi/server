package com.project.meomi.domain.user.presentation.data.request

import javax.validation.constraints.NotNull
import javax.validation.constraints.Pattern

data class SignUpRequest(
    @field:NotNull
    @field:Pattern(regexp = "^[a-zA-Z0-9]+@gsm.hs.kr\$")
    val email: String,
    @field:NotNull
    val password: String,
    @field:NotNull
    val name: String,
    @field:NotNull
    val stuNum: Int
)