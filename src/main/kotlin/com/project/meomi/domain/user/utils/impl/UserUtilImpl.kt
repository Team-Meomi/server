package com.project.meomi.domain.user.utils.impl

import com.project.meomi.domain.user.domain.User
import com.project.meomi.domain.user.domain.repository.UserRepository
import com.project.meomi.domain.user.exception.UserNotFoundException
import com.project.meomi.domain.user.utils.UserUtil
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class UserUtilImpl(
    private val userRepository: UserRepository
): UserUtil {

    @Transactional(readOnly = true, rollbackFor = [Exception::class])
    override fun currentUser(): User {
        val email = SecurityContextHolder.getContext().authentication.name
        return userRepository.findUserByEmail(email)
            ?: throw UserNotFoundException()
    }

}