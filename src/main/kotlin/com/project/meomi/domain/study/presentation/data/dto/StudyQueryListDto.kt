package com.project.meomi.domain.study.presentation.data.dto

import java.time.LocalDate

data class StudyQueryListDto(
    val id: Long,
    val title: String,
    val category: String,
    val date: LocalDate,
    val studyType: String,
)