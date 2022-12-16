package com.project.meomi.domain.conference.presentation.data.request

import jakarta.validation.constraints.NotBlank
import java.time.LocalDate

data class CreateConferenceRequest(
    @field:NotBlank
    val title: String,
    @field:NotBlank
    val content: String,
    @field:NotBlank
    val date: LocalDate,
    @field:NotBlank
    val startTime: Int,
    @field:NotBlank
    val endTime: Int
)