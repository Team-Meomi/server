package com.project.meomi.domain.user.service.impl

import com.project.meomi.domain.user.domain.User
import com.project.meomi.domain.user.domain.repository.UserRepository
import com.project.meomi.domain.user.exception.InvalidTokenException
import com.project.meomi.domain.user.exception.RefreshTokenExpiredException
import com.project.meomi.domain.user.exception.UserNotFoundException
import com.project.meomi.domain.user.presentation.data.dto.ReissueTokenDto
import com.project.meomi.domain.user.presentation.data.dto.TokenDto
import com.project.meomi.domain.user.service.ReissueTokenService
import com.project.meomi.domain.user.utils.JwtTokenUtil
import com.project.meomi.global.security.jwt.JwtTokenProvider
import org.springframework.stereotype.Component

@Component
class ReissueTokenServiceImpl(
    private val userRepository: UserRepository,
    private val jwtTokenProvider: JwtTokenProvider,
    private val jwtTokenUtil: JwtTokenUtil
): ReissueTokenService {

    override fun reissueToken(dto: ReissueTokenDto): TokenDto {
        val user = userRepository.findUserByEmail(jwtTokenProvider.getUserEmail(dto.refreshToken))
            ?: throw UserNotFoundException()

        return jwtTokenUtil.generateJwtToken(tokenIsValid(user, dto.refreshToken))
    }

    private fun tokenIsValid(user: User, refreshToken: String): String {
        when (refreshToken) {
            user.refreshToken -> {
                jwtTokenProvider.isTokenExpired(refreshToken)
                    .let {
                        if (it) throw RefreshTokenExpiredException()
                    }
            }
            else -> throw InvalidTokenException()
        }
        return user.email
    }

}