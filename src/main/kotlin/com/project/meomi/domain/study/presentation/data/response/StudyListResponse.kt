package com.project.meomi.domain.study.presentation.data.response

import java.time.LocalDate

data class StudyListResponse(
    val id: Long,
    val title: String,
    val category: String,
    val date: LocalDate,
    val type: String,
)