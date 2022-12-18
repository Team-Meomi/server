package com.project.meomi.domain.conference.presentation.data.response

import com.project.meomi.domain.user.presentation.data.type.Gender
import java.time.LocalDate

data class ConferenceResponse(
    val id: Long,
    val title: String,
    val content: String,
    val category: String,
    val date: LocalDate,
    val startTime: Int,
    val endTime: Int,
    val user: UserResponse
) {
    data class UserResponse(
        val id: Long,
        val name: String,
        val gender: Gender
    )
}