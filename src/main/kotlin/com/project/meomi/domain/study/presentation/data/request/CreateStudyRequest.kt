package com.project.meomi.domain.study.presentation.data.request

import java.time.LocalDate
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class CreateStudyRequest(
    @field:NotBlank
    val title: String,
    @field:NotBlank
    val content: String,
    @field:NotBlank
    val category: String,
    @field:NotBlank
    val date: LocalDate,
    @field:NotBlank
    val maxCount: Int,
    @field:NotBlank
    val studyType: String
)