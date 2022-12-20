package com.project.meomi.domain.study.presentation.data.response

import com.project.meomi.domain.study.presentation.data.dto.StudyQueryDto
import java.time.LocalDate

data class StudyResponse(
    val id: Long,
    val title: String,
    val content: String,
    val category: String,
    val date: LocalDate,
    val studyType: String,
    val isMine: Boolean,
    val isStatus: Boolean,
    val writer: StudyQueryDto.UserResponse,
    val count: StudyQueryDto.CountResponse,
    val list: List<StudyQueryDto.UserResponse>
)