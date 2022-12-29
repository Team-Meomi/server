package com.project.meomi.domain.user.utils.impl

import com.project.meomi.domain.user.domain.User
import com.project.meomi.domain.user.presentation.data.dto.ReissueTokenDto
import com.project.meomi.domain.user.presentation.data.dto.UserDto
import com.project.meomi.domain.user.presentation.data.request.SignInRequest
import com.project.meomi.domain.user.presentation.data.request.SignUpRequest
import com.project.meomi.domain.user.presentation.data.type.Role
import com.project.meomi.domain.user.utils.UserConverter
import org.springframework.stereotype.Component
import java.util.*

@Component
class UserConverterImpl : UserConverter {

    override fun toDto(request: SignUpRequest): UserDto =
        UserDto(id = -1, email = request.email, password = request.password, name = request.name, stuNum = request.stuNum)

    override fun toDto(request: SignInRequest): UserDto =
        UserDto(id = -1, email = request.email, password = request.password, name = "", stuNum = -1)

    override fun toDto(refreshToken: String): ReissueTokenDto =
        ReissueTokenDto(refreshToken = refreshToken)

    override fun toDto(id: Long): UserDto =
        UserDto(id = id, email = "", password = "", name = "", stuNum = -1)

    override fun toEntity(dto: UserDto, encodePassword: String): User =
        User(id = -1, email = dto.email, password = encodePassword, name = dto.name, stuNum = dto.stuNum, refreshToken = "", role = Collections.singletonList(Role.USER))

}