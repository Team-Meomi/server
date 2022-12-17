package com.project.meomi.domain.user.presentation.data.dto

import com.project.meomi.domain.user.presentation.data.type.Gender

data class UserDto(
    val userId: Long,
    val email: String,
    val password: String,
    val name: String,
    val stuNum: Int
)