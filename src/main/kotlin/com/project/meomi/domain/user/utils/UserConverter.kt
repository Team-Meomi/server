package com.project.meomi.domain.user.utils

import com.project.meomi.domain.user.domain.User
import com.project.meomi.domain.user.presentation.data.dto.ReissueTokenDto
import com.project.meomi.domain.user.presentation.data.dto.UserDto
import com.project.meomi.domain.user.presentation.data.request.SignInRequest
import com.project.meomi.domain.user.presentation.data.request.SignUpRequest

interface UserConverter {

    fun toDto(request: SignUpRequest): UserDto
    fun toDto(request: SignInRequest): UserDto
    fun toDto(refreshToken: String): ReissueTokenDto
    fun toDto(id: Long): UserDto
    fun toEntity(dto: UserDto, encodePassword: String): User

}