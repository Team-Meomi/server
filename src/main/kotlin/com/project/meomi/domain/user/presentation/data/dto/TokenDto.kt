package com.project.meomi.domain.user.presentation.data.dto

import java.time.LocalDateTime

class TokenDto(
    val accessToken: String,
    val refreshToken: String,
    val expiredAt: LocalDateTime
)