package com.project.meomi.domain.user.utils.impl

import com.project.meomi.domain.user.domain.User
import com.project.meomi.domain.user.presentation.data.dto.TokenDto
import com.project.meomi.domain.user.presentation.data.dto.UserQueryDto
import com.project.meomi.domain.user.presentation.data.response.TokenResponse
import com.project.meomi.domain.user.presentation.data.response.UserInfoResponse
import com.project.meomi.domain.user.utils.UserQueryConverter
import org.springframework.stereotype.Component

@Component
class UserQueryConverterImpl: UserQueryConverter {

    override fun toQueryDto(user: User): UserQueryDto =
        UserQueryDto(user.name, user.stuNum)

    override fun toResponse(dto: TokenDto): TokenResponse =
        TokenResponse(accessToken = dto.accessToken, refreshToken = dto.refreshToken, expiredAt = dto.expiredAt)

    override fun toResponse(dto: UserQueryDto): UserInfoResponse =
        UserInfoResponse(dto.name, dto.stuNum)

}