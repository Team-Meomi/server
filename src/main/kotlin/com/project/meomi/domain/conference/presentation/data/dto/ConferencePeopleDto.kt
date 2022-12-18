package com.project.meomi.domain.conference.presentation.data.dto

import com.project.meomi.domain.user.presentation.data.type.Gender

data class ConferencePeopleDto(
    val list: List<UserResponse>
) {
    data class UserResponse(
        val id: Long,
        val stuNum: Int,
        val name: String,
        val gender: Gender
    )
}