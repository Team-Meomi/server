package com.project.meomi.domain.conference.presentation.data.response

import com.project.meomi.domain.conference.presentation.data.dto.ConferenceQueryDto
import com.project.meomi.domain.user.domain.User
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
    val isMine: Boolean,
    val isStatus: Boolean,
    val writer: ConferenceQueryDto.UserResponse,
    val count: ConferenceQueryDto.CountResponse,
    val list: List<ConferenceQueryDto.UserResponse>
)