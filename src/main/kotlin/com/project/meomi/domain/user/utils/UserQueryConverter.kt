package com.project.meomi.domain.user.utils

import com.project.meomi.domain.user.domain.User
import com.project.meomi.domain.user.presentation.data.dto.TokenDto
import com.project.meomi.domain.user.presentation.data.dto.UserQueryDto
import com.project.meomi.domain.user.presentation.data.response.TokenResponse
import com.project.meomi.domain.user.presentation.data.response.UserInfoResponse

interface UserQueryConverter {

    fun toQueryDto(user: User): UserQueryDto
    fun toResponse(dto: TokenDto): TokenResponse
    fun toResponse(dto: UserQueryDto): UserInfoResponse

}