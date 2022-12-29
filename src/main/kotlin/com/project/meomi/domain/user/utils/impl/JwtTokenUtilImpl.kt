package com.project.meomi.domain.user.utils.impl

import com.project.meomi.domain.user.domain.repository.UserRepository
import com.project.meomi.domain.user.exception.UserNotFoundException
import com.project.meomi.domain.user.presentation.data.dto.TokenDto
import com.project.meomi.domain.user.utils.JwtTokenUtil
import com.project.meomi.global.security.jwt.JwtTokenProvider
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class JwtTokenUtilImpl(
    private val jwtTokenProvider: JwtTokenProvider,
    private val userRepository: UserRepository
): JwtTokenUtil {

    @Transactional(rollbackFor = [Exception::class])
    override fun generateJwtToken(email: String): TokenDto {
        val user = userRepository.findUserByEmail(email)
            ?: throw UserNotFoundException()
        val accessToken = jwtTokenProvider.generateAccessToken(email, user.role)
        val refreshToken = jwtTokenProvider.generateRefreshToken(email, user.role)
        user.updateRefreshToken(refreshToken)
        return TokenDto(accessToken, refreshToken, jwtTokenProvider.getExpiredAt())
    }

}