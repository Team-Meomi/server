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
    val writer: StudyQueryDto.UserDto,
    val count: StudyQueryDto.CountDto,
    val list: List<StudyQueryDto.UserDto>
)