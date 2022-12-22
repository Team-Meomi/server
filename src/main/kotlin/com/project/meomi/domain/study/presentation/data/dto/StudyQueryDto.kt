package com.project.meomi.domain.study.presentation.data.dto

import java.time.LocalDate

data class StudyQueryDto(
    val id: Long,
    val title: String,
    val content: String,
    val category: String,
    val date: LocalDate,
    val studyType: String,
    val isMine: Boolean,
    val isStatus: Boolean,
    val writer: UserDto,
    val count: CountDto,
    val list: List<UserDto>
) {
    data class CountDto(
        val count: Int,
        val maxCount: Int
    )

    data class UserDto(
        val id: Long,
        val stuNum: Int,
        val name: String,
    )
}