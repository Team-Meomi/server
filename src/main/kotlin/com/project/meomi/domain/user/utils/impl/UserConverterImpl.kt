package com.project.meomi.domain.user.utils.impl

import com.project.meomi.domain.user.domain.User
import com.project.meomi.domain.user.presentation.data.dto.ReissueTokenDto
import com.project.meomi.domain.user.presentation.data.dto.TokenDto
import com.project.meomi.domain.user.presentation.data.dto.UserDto
import com.project.meomi.domain.user.presentation.data.request.SignInRequest
import com.project.meomi.domain.user.presentation.data.request.SignUpRequest
import com.project.meomi.domain.user.presentation.data.response.TokenResponse
import com.project.meomi.domain.user.presentation.data.type.Gender
import com.project.meomi.domain.user.presentation.data.type.Role
import com.project.meomi.domain.user.utils.UserConverter
import org.springframework.stereotype.Component
import java.util.*

@Component
class UserConverterImpl : UserConverter {

    override fun toDto(request: SignUpRequest): UserDto =
        UserDto(-1, request.email, request.password, request.name, request.stuNum)

    override fun toDto(request: SignInRequest): UserDto =
        UserDto(-1, request.email, request.password, "", "")

    override fun toDto(refreshToken: String): ReissueTokenDto =
        ReissueTokenDto(refreshToken)

    override fun toEntity(dto: UserDto, encodePassword: String): User =
        User(-1, dto.email, encodePassword, dto.name, dto.stuNum, Gender.MAN, Collections.singletonList(Role.USER), "")

    override fun toResponse(dto: TokenDto): TokenResponse =
        TokenResponse(dto.accessToken, dto.refreshToken, dto.expiredAt)

}