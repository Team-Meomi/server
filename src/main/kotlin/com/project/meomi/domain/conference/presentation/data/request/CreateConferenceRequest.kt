package com.project.meomi.domain.conference.presentation.data.request

import java.time.LocalDate
import javax.validation.constraints.NotNull

data class CreateConferenceRequest(
    @field:NotNull
    val title: String,
    @field:NotNull
    val content: String,
    @field:NotNull
    val category: String,
    @field:NotNull
    val date: LocalDate,
    @field:NotNull
    val startTime: Int,
    @field:NotNull
    val endTime: Int
)