package com.project.meomi.domain.study.presentation.data.dto

import com.project.meomi.domain.user.presentation.data.type.Gender
import java.time.LocalDate

data class StudyQueryDto(
    val id: Long,
    val title: String,
    val content: String,
    val category: String,
    val date: LocalDate,
    val isMine: Boolean,
    val isStatus: Boolean,
    val writer: UserResponse,
    val count: CountResponse,
    val list: List<UserResponse>
) {
    data class CountResponse(
        val count: Int,
        val maxCount: Int
    )

    data class UserResponse(
        val id: Long,
        val stuNum: Int,
        val name: String,
        val gender: Gender
    )
}