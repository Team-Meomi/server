package com.project.meomi.domain.user.service.impl

import com.project.meomi.domain.user.presentation.data.dto.TokenDto
import com.project.meomi.domain.user.presentation.data.dto.UserDto
import com.project.meomi.domain.user.presentation.data.type.ValidatorType
import com.project.meomi.domain.user.service.SignInService
import com.project.meomi.domain.user.utils.JwtTokenUtil
import com.project.meomi.domain.user.utils.UserValidator
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class SignInServiceImpl(
    private val userValidator: UserValidator,
    private val jwtTokenUtil: JwtTokenUtil
): SignInService {

    @Transactional(rollbackFor = [Exception::class])
    override fun signIn(dto: UserDto): TokenDto {
        userValidator.validate(ValidatorType.SIGNIN, dto)
            .let { return jwtTokenUtil.generateJwtToken(dto.email) }
    }

}