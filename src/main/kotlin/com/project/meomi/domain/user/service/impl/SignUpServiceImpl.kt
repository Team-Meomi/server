package com.project.meomi.domain.user.service.impl

import com.project.meomi.domain.user.domain.repository.UserRepository
import com.project.meomi.domain.user.presentation.data.dto.UserDto
import com.project.meomi.domain.user.presentation.data.type.ValidatorType
import com.project.meomi.domain.user.service.SignUpService
import com.project.meomi.domain.user.utils.UserConverter
import com.project.meomi.domain.user.utils.UserValidator
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.lang.Exception

@Service
class SignUpServiceImpl(
    private val userRepository: UserRepository,
    private val userValidator: UserValidator,
    private val userConverter: UserConverter,
    private val passwordEncoder: PasswordEncoder
): SignUpService {

    @Transactional(rollbackFor = [Exception::class])
    override fun signUp(dto: UserDto) {
        userValidator.validate(ValidatorType.SIGNUP, dto)
            .let { userConverter.toEntity(dto, passwordEncoder.encode(dto.password)) }
            .let { userRepository.save(it) }
    }

}