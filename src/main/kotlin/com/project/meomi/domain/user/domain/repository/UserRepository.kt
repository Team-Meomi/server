package com.project.meomi.domain.user.domain.repository

import com.project.meomi.domain.user.domain.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<User, Long> {
    fun findUserByEmail(email: String): User?
    fun existsUserByEmail(email: String): Boolean
}