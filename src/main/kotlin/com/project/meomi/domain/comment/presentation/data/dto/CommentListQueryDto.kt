package com.project.meomi.domain.comment.presentation.data.dto

data class CommentListQueryDto(
    val id: Long,
    val comment: String,
    val isMine: Boolean,
    val writer: UserDto
) {
    data class UserDto(
        val id: Long,
        val name: String,
        val stuNum: Int
    )
}
