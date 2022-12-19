package com.project.meomi.domain.conference.presentation.data.dto

import java.time.LocalDate

data class ConferenceDto(
    val id: Long,
    val title: String,
    val content: String,
    val category: String,
    val date: LocalDate,
    val startTime: Int,
    val endTime: Int,
    val maxCount: Int
)