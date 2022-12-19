package com.project.meomi.domain.study.presentation.data.dto

import java.time.LocalDate

data class StudyDto(
    val id: Long,
    val title: String,
    val content: String,
    val category: String,
    val date: LocalDate,
)