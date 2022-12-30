package com.project.meomi.domain.study.presentation.data.request

import java.time.LocalDate
import javax.validation.constraints.NotNull

data class UpdateStudyRequest(
    @field:NotNull
    val title: String,
    @field:NotNull
    val content: String,
    @field:NotNull
    val category: String,
    @field:NotNull
    val date: LocalDate,
    @field:NotNull
    val maxCount: Int
)