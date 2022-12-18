package com.project.meomi.domain.conference.presentation.data.request

import java.time.LocalDate
import javax.validation.constraints.NotBlank

data class UpdateConferenceRequest(
    @field:NotBlank
    val title: String,
    @field:NotBlank
    val content: String,
    @field:NotBlank
    val category: String,
    @field:NotBlank
    val date: LocalDate,
    @field:NotBlank
    val startTime: Int,
    @field:NotBlank
    val endTime: Int
)