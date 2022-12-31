package com.project.meomi.global.security.authentication

import com.project.meomi.domain.user.domain.repository.UserRepository
import com.project.meomi.domain.user.exception.UserNotFoundException
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AuthDetailsService(
    private val userRepository: UserRepository
): UserDetailsService {

    @Transactional(readOnly = true, rollbackFor = [Exception::class])
    override fun loadUserByUsername(username: String): UserDetails =
        AuthDetails(
            userRepository.findUserByEmail(username) ?: throw UserNotFoundException()
        )

}