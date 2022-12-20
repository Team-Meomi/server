package com.project.meomi.domain.user.presentation.data.dto

data class UserDto(
    val id: Long,
    val email: String,
    val password: String,
    val name: String,
    val stuNum: Int
)