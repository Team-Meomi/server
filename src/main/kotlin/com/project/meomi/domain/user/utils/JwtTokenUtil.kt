package com.project.meomi.domain.user.utils

import com.project.meomi.domain.user.presentation.data.dto.TokenDto

interface JwtTokenUtil {

    fun generateJwtToken(email: String): TokenDto

}