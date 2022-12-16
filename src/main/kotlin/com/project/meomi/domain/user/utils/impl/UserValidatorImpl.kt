package com.project.meomi.domain.user.utils.impl

import com.project.meomi.domain.user.domain.repository.UserRepository
import com.project.meomi.domain.user.exception.DuplicateEmailException
import com.project.meomi.domain.user.exception.PasswordNotCorrectException
import com.project.meomi.domain.user.exception.UserNotFoundException
import com.project.meomi.domain.user.presentation.data.dto.UserDto
import com.project.meomi.domain.user.presentation.data.type.ValidatorType
import com.project.meomi.domain.user.utils.UserValidator
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component

@Component
class UserValidatorImpl(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder
): UserValidator {

    override fun validate(validatorType: ValidatorType, dto: UserDto) {
        when(validatorType) {
            ValidatorType.SIGNUP -> validateSignUp(dto)
            ValidatorType.SIGNIN -> validateSignIn(dto)
        }
    }

    private fun validateSignUp(dto: UserDto) {
        if(userRepository.existsUserByEmail(dto.email)) {
            throw DuplicateEmailException()
        }
    }

    private fun validateSignIn(dto: UserDto) {
        userRepository.findUserByEmail(dto.email)
            .let { it ?: throw UserNotFoundException() }
            .let { passwordEncoder.matches(dto.password, it.password) }
            .let {
                if(it) return
                else throw PasswordNotCorrectException()
            }
    }

}