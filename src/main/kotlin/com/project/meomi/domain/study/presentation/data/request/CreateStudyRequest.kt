package com.project.meomi.domain.study.presentation.data.request

import java.time.LocalDate

data class CreateStudyRequest(
    val title: String,
    val content: String,
    val category: String,
    val date: LocalDate,
    val startTime: Int,
    val endTime: Int,
)