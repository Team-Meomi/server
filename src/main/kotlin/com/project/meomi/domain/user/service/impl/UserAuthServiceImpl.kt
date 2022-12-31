package com.project.meomi.domain.user.service.impl

import com.project.meomi.domain.user.domain.User
import com.project.meomi.domain.user.domain.repository.UserRepository
import com.project.meomi.domain.user.exception.InvalidTokenException
import com.project.meomi.domain.user.exception.RefreshTokenExpiredException
import com.project.meomi.domain.user.exception.UserNotFoundException
import com.project.meomi.domain.user.presentation.data.dto.ReissueTokenDto
import com.project.meomi.domain.user.presentation.data.dto.TokenDto
import com.project.meomi.domain.user.presentation.data.dto.UserDto
import com.project.meomi.domain.user.presentation.data.type.ValidatorType
import com.project.meomi.domain.user.service.UserAuthService
import com.project.meomi.domain.user.utils.JwtTokenUtil
import com.project.meomi.domain.user.utils.UserConverter
import com.project.meomi.domain.user.utils.UserUtil
import com.project.meomi.domain.user.utils.UserValidator
import com.project.meomi.global.security.jwt.JwtTokenProvider
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserAuthServiceImpl(
    private val userRepository: UserRepository,
    private val userValidator: UserValidator,
    private val userConverter: UserConverter,
    private val passwordEncoder: PasswordEncoder,
    private val jwtTokenUtil: JwtTokenUtil,
    private val jwtTokenProvider: JwtTokenProvider,
    private val userUtil: UserUtil
): UserAuthService {

    @Transactional(rollbackFor = [java.lang.Exception::class])
    override fun signUp(dto: UserDto) {
        userValidator.validate(ValidatorType.SIGNUP, dto)
            .let { userConverter.toEntity(dto, passwordEncoder.encode(dto.password)) }
            .let { userRepository.save(it) }
    }

    @Transactional(rollbackFor = [Exception::class])
    override fun signIn(dto: UserDto): TokenDto {
        userValidator.validate(ValidatorType.SIGNIN, dto)
            .let { return jwtTokenUtil.generateJwtToken(dto.email) }
    }

    @Transactional(rollbackFor = [Exception::class])
    override fun reissueToken(dto: ReissueTokenDto): TokenDto {
        val user = userRepository.findUserByEmail(jwtTokenProvider.getUserEmail(dto.refreshToken))
            ?: throw UserNotFoundException()

        return jwtTokenUtil.generateJwtToken(tokenIsValid(user, dto.refreshToken))
    }

    @Transactional(rollbackFor = [Exception::class])
    override fun logout() {
        userUtil.currentUser().updateRefreshToken("")
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