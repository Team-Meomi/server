package com.project.meomi.domain.comment.presentation.data.response

import com.project.meomi.domain.comment.presentation.data.dto.CommentListQueryDto

data class CommentListResponse(
    val id: Long,
    val comment: String,
    val isMine: Boolean,
    val writer: CommentListQueryDto.UserDto
)