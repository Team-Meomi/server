package com.project.meomi.domain.conference.presentation.data.dto

import com.project.meomi.domain.user.domain.User
import java.time.LocalDate

data class ConferenceQueryDto(
    val id: Long,
    val title: String,
    val content: String,
    val category: String,
    val date: LocalDate,
    val startTime: Int,
    val endTime: Int,
    val isMine: Boolean,
    val user: User
)