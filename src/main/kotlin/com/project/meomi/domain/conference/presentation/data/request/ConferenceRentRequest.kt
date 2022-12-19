package com.project.meomi.domain.conference.presentation.data.request

import java.time.LocalDate
import javax.validation.constraints.NotNull

data class ConferenceRentRequest(
    @field:NotNull
    val date: LocalDate,
    @field:NotNull
    val startTime: Int,
    @field:NotNull
    val endTime: Int
)